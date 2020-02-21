CREATE DEFINER=`root`@`localhost` PROCEDURE `getNationalSponsorMembers`(IN rptDate date, IN fyEndDate date, IN prmGrpCode VARCHAR(10))
BEGIN
SELECT 
  distinct m.api_guid, mvd.promotional_code, m.alternate_emails, m.amount, m.date_processed, m.email, m.email_bounced, m.expiry_date, m.first_name, m.last_name,
  m.last_renewed, m.last_updated, m.member_type, m.membership, m.middle_name, m.mobile, m.name_prefix, m.name_title , m.nick_name, m.payment_type,
  m.primary_group_code, m.registration_date, m.sponsership_code, m.tenancy_id, m.employer, s.sponsor,s.discount_code, m.home_address, m.official_details, m.personal_info 
FROM
    members m
        INNER JOIN
    members_version_data mvd ON m.api_guid = mvd.guid
        INNER JOIN
    sponsor s ON s.discount_code = mvd.promotional_code
        AND mvd.primary_group_code IN (SELECT 
            primary_group_code
        FROM
            chapter
        WHERE
            associated_with = prmGrpCode
                OR primary_group_code = prmGrpCode)
        AND DATE(mvd.reporting_date) <= rptDate
        AND mvd.payment_type = 'BILL ME'
        AND s.sponser_level = 'NATIONAL'
		AND s.type = 'SPONSOR'
        AND s.primary_group_code = 'NA'
        AND DATE(mvd.expiry_date) > fyEndDate
        AND mvd.membership_type LIKE '%PROFESSIONAL%';
END