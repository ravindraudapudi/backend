CREATE DEFINER=`root`@`%` PROCEDURE `getPaidMembers`(IN rptDate date, IN fyEndDate date, IN prmGrpCode VARCHAR(10))
BEGIN
SELECT 
    m.*
FROM
    members m
        INNER JOIN
    members_version_data mvd ON mvd.guid = m.api_guid
WHERE
    mvd.primary_group_code IN (SELECT 
            primary_group_code
        FROM
            chapter
        WHERE
            associated_with = prmGrpCode
                OR primary_group_code = prmGrpCode)
        AND DATE(mvd.reporting_date) <= rptDate
        AND mvd.payment_type = 'CREDIT CARD'
        AND mvd.expiry_date > fyEndDate
        AND mvd.membership_type LIKE '%PROFESSIONAL%' 
UNION ALL SELECT 
    *
FROM
    members
WHERE
    membership LIKE '%LIFETIME%'
        AND primary_group_code = prmGrpCode;
END