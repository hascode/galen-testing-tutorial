@objects
	main_container	xpath      //*[@class='Main']
	page_heading	css	.logo-name a
	post_heading	css	.post h2
	post_image		css #attachment_1292 img

= Page Header Section =
	page_heading:
		width ~ 20 % of main_container/width
		text is "hasCode.com"

= Post Section =	
	post_heading:
		text contains "Maven"
		
	post_image:
		image file reference-image.png, error 5%
