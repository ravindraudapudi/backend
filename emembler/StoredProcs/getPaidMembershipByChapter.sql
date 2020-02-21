CREATE DEFINER=`root`@`%` PROCEDURE `getPaidMembershipByChapter`(IN rptDate date, IN fyEndDate date, IN prmGrpCode VARCHAR(10))
BEGIN
	SELECT ( SELECT 
    count(distinct mvd.guid)
FROM
    members_version_data mvd
WHERE
		mvd.primary_group_code IN (SELECT primary_group_code FROM chapter WHERE  associated_with = prmGrpCode  OR primary_group_code = prmGrpCode)
        AND DATE(mvd.reporting_date) <= rptDate
        AND mvd.payment_type = 'CREDIT CARD'
        AND DATE(mvd.expiry_date) > fyEndDate
        AND mvd.membership_type like '%PROFESSIONAL%') + (SELECT 
            COUNT(*)
        FROM
            members
        WHERE
            members.membership LIKE '%LIFETIME%'
                AND members.primary_group_code = prmGrpCode) AS sum;
END