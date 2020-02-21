CREATE DEFINER=`root`@`localhost` PROCEDURE `getMonthlyPaidExpiredMembers`(IN year VARCHAR(10),IN month VARCHAR(10), IN rptDate date, IN prmGrpCode VARCHAR(10))
BEGIN
Select 
  distinct m.api_guid, m.alternate_emails, m.amount, m.date_processed, m.email, m.email_bounced, mvd.expiry_date, m.first_name, m.last_name,
  m.last_renewed, m.last_updated, m.member_type, m.membership, m.middle_name, m.mobile, m.name_prefix, m.name_title , m.nick_name, m.payment_type,
  m.primary_group_code, m.discount_code, m.sponsor, m.registration_date, m.sponsership_code, m.tenancy_id, m.employer, m.home_address, m.official_details, m.personal_info ,mvd.promotional_code
  from members m  inner join  members_version_data mvd ON m.api_guid = mvd.guid 
    AND
        MONTH(mvd.expiry_date) = month
            AND YEAR(mvd.expiry_date) = year
            AND DATE(mvd.reporting_date) <= rptDate
            AND mvd.payment_type = 'CREDIT CARD'
        AND mvd.primary_group_code IN (SELECT 
            primary_group_code
        FROM
            chapter
        WHERE
            associated_with = prmGrpCode
                OR primary_group_code = prmGrpCode)
        AND mvd.membership_type LIKE '%PROFESSIONAL%';
END