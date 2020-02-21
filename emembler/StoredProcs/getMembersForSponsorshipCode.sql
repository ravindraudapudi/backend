CREATE DEFINER=`root`@`localhost` PROCEDURE `getMembersForSponsorshipCode`(IN sponsorshipCode VARCHAR(100))
BEGIN
select * from members where api_guid in 
			 ( select member_api_guid from transaction 
			 WHERE sponsership_code = sponsorshipCode 
			 AND sponsership_code not in ('','NA')) ;
END