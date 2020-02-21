CREATE DEFINER=`root`@`localhost` PROCEDURE `getExpiredMembersInCurrentFy`(IN startDate  date, IN reportDate date, IN primaryGroupCode VARCHAR(10))
BEGIN
 SELECT 
    count(distinct m.api_guid)
FROM
    members AS m
        INNER JOIN
    (SELECT 
        guid
    FROM
        members_version_data
    WHERE
        DATE(expiry_date) >= startDate
            AND DATE(expiry_date) <= reportDate
            AND reporting_date <= reportDate
            AND payment_type = 'CREDIT CARD') ev ON ev.guid = m.api_guid
        AND m.primary_group_code IN (SELECT 
            primary_group_code
        FROM
            chapter
        WHERE
            associated_with = primaryGroupCode
                OR primary_group_code = primaryGroupCode)
        AND m.membership LIKE '%PROFESSIONAL%';
END