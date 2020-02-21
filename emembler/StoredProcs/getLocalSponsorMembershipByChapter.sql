CREATE DEFINER=`root`@`localhost` PROCEDURE `getLocalSponsorMembershipByChapter`(IN rptDate date, IN fyEndDate date, IN prmGrpCode VARCHAR(10))
BEGIN
SELECT 
    count(*)
FROM
    members_version_data mvd  inner join sponsor s on s.discount_code = mvd.promotional_code
AND
		mvd.primary_group_code IN (SELECT primary_group_code FROM chapter WHERE  associated_with = prmGrpCode  OR primary_group_code = prmGrpCode)
        AND DATE(reporting_date) <= rptDate
        AND mvd.payment_type = 'BILL ME'
        AND s.primary_group_code != 'NA'
        AND s.type = 'SPONSOR'
        AND s.sponser_level in  ('NATIONAL','CHAPTER')
        AND mvd.expiry_date > fyEndDate
        AND mvd.membership_type like '%PROFESSIONAL%';
END