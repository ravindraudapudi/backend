CREATE DEFINER=`rmusr`@`%` PROCEDURE `getOtherMembers`(IN rptDate date, IN fyEndDate date, IN primaryGroupCode VARCHAR(10))
BEGIN
SELECT 
  MVD.promotional_code,m.api_guid, m.alternate_emails, m.amount, m.date_processed, m.email, m.email_bounced, m.expiry_date, m.first_name, m.last_name,
  m.last_renewed, m.last_updated, m.member_type, m.membership, m.middle_name, m.mobile, m.name_prefix, m.name_title , m.nick_name, m.payment_type,
  m.primary_group_code, m.registration_date, m.sponsership_code, m.tenancy_id, m.employer, m.sponsor,m.discount_code, m.home_address, m.official_details, m.personal_info,m.gender,m.mobile_area_code
FROM
    members_version_data MVD  inner join members m ON 
    m.api_guid = MVD.guid
AND
    MVD.primary_group_code IN (SELECT 
            primary_group_code
        FROM
            chapter
        WHERE
            associated_with = primaryGroupCode
                OR primary_group_code = primaryGroupCode)
        AND MVD.reporting_date = rptDate
        AND MVD.expiry_date > fyEndDate
        AND MVD.membership_type LIKE '%PROFESSIONAL%' 
		AND m.api_guid not in (SELECT 
   m.api_guid
FROM members m
INNER JOIN (SELECT MVD.* FROM members_version_data AS MVD
				INNER JOIN (SELECT MAX(transaction_Id) as transaction_id, MVD.guid FROM members_version_data as MVD
								INNER JOIN (SELECT primary_group_code FROM chapter
												WHERE associated_with = primaryGroupCode OR primary_group_code =  primaryGroupCode) as PGC
									ON MVD.primary_group_code = PGC.primary_group_code
								WHERE NOT MVD.transaction_Id = 0
									 AND MVD.membership_type LIKE '%PROFESSIONAL%'
								group by MVD.guid) 
                    AS latest_trans ON MVD.transaction_id = latest_trans.transaction_id AND MVD.guid = latest_trans.guid AND MVD.payment_type in  ('BILL ME', 'CREDIT CARD') AND MVD.reporting_Date <= rptDate  AND MVD.expiry_date > fyEndDate
			) as MVD ON m.api_guid = MVD.guid) UNION 
            SELECT 
   mvd.promotional_code,m.api_guid, m.alternate_emails, m.amount, m.date_processed, m.email, m.email_bounced, m.expiry_date, m.first_name, m.last_name,
  m.last_renewed, m.last_updated, m.member_type, m.membership, m.middle_name, m.mobile, m.name_prefix, m.name_title , m.nick_name, m.payment_type,
  m.primary_group_code, m.registration_date, m.sponsership_code, m.tenancy_id, m.employer, m.sponsor,m.discount_code, m.home_address, m.official_details, m.personal_info,m.gender,m.mobile_area_code
FROM
    members m
        INNER JOIN
    members_version_data mvd ON m.api_guid = mvd.guid
        AND mvd.primary_group_code IN (SELECT 
            primary_group_code
        FROM
            chapter
        WHERE
            associated_with = primaryGroupCode
                OR primary_group_code = primaryGroupCode)
        AND DATE(mvd.reporting_date) <= rptDate
        AND mvd.payment_type = 'BILL ME'
        AND mvd.promotional_code not in (SELECT discount_code from sponsor s where s.discount_code != 'NAT')
        AND DATE(mvd.expiry_date) > fyEndDate
        AND mvd.membership_type LIKE '%PROFESSIONAL%'; 
END