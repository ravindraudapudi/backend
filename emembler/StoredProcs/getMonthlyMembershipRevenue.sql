CREATE DEFINER=`root`@`localhost` PROCEDURE `getMonthlyMembershipRevenue`(year VARCHAR(10), month VARCHAR(10), IN rptDate date, IN prmGrpCode VARCHAR(10))
BEGIN
select  sum(mvd.amount)  from  members  m  
			 			        inner join  members_version_data  mvd 
			                     on mvd.guid = m.api_guid  where  
			 			        month(mvd.date_processed) = month
			 			       and year(mvd.date_processed) = year
			 			            and date(mvd.reporting_date) <= rptDate   
			 			            and mvd.payment_type = 'CREDIT CARD' 
			 			        and m.primary_group_code in (select   primary_group_code  
			 			        from   chapter  where  
			 			            associated_with = prmGrpCode  
			 			                or primary_group_code = prmGrpCode)  
			 			        and m.membership like '%PROFESSIONAL%';
END