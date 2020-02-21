CREATE DEFINER=`root`@`localhost` PROCEDURE `getTotalActiveMembershipByChapter`(IN rptDate date, IN fyEndDate date, IN prmGrpCode VARCHAR(10))
BEGIN
SELECT (SELECT 
    COUNT(*)
FROM
    members_version_data
WHERE
    primary_group_code IN (SELECT 
            primary_group_code
        FROM
            chapter
        WHERE
            associated_with = prmGrpCode
                OR primary_group_code = prmGrpCode)
        AND DATE(reporting_date) = rptDate
        AND expiry_date > fyEndDate
        AND membership_type LIKE '%PROFESSIONAL%') + (SELECT 
            COUNT(*)
        FROM
            members
        WHERE
            members.membership LIKE '%LIFETIME%'
                AND members.primary_group_code = prmGrpCode) AS sum;
END