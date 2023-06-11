# NewsService

° For the building of this application JAva, Spring and Mockito were used.

° This is the Home Page: 
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/a65e19c5-f060-4813-9d35-81f39cd1b162)

° This is the Registration Page: 
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/b704d19c-0021-4ff9-a99d-0f79328087c6)
 -> After a successful registration, the user is being redirected to the Home Page
 
 ° This is the Login Page:
 ![image](https://github.com/BroskiVankis/NewsService/assets/78420177/3d5fc51a-40eb-4ac5-9d63-b3e3c51b1365)
-> Again after a successful login, the user is being redirected to the Home Page

° There are three types of roles: "ADMIN", "PUBLISHER" and "READER"
-> The Navigation Bar of the ADMIN looks slightly different than the other two Role Users. It one more button which is Admin, where he can ADD, EDIT and DELETE other users: 
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/94ca9d08-5296-4114-a1de-3ea10425deb0)
-> Once this button is triggered a table view is being rendered where all the users are being fetched from the BD:
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/17dda620-ef08-4b1b-bda8-e8c844bdc1c6)
-> This is the Add News User view, where the ADMIN can add a new User: 
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/e93619cc-5a84-4152-8544-1f02b4864172)
-> This is the Edit User view, where the ADMIN can edit an alreadz existing user: 
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/ad2df55c-0763-42d1-adf6-916dda020c84)
-> Once the ADMIN clicks on the Delete button, the user he wanted to delete, gets deleted, on the next screenshot it can be seen that one user is missing(got deleted):
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/9b91d10c-798e-44c0-b033-90cd03836099)

° The users wtih ADMIN and PUBLISHER roles can also add new news articles: 
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/39f6d1e7-d083-4c29-8531-89d2d4186897)

° in the All News view, where all of the news are displayed, a user can click on next and previous, to get more news and on each News Article there is a DETAILS link, where a user can open the article and see all of the details for it:
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/384ad7d7-ca33-4ed9-bd85-ab4e57f835ba)

° ADMIN and PUBLISHER users have the option to delete and to edit the article, only if they are the ones that created it:
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/aafa6d94-dedd-4a71-a590-347e0b1a1720)
-> If a user who did not created the article tries to delete it, gets a custom Error message, that he is not authorized to do so:
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/885275f8-5a49-48bb-9278-58fe084f195b)


° This is the Edit Article view, where changes on the article can be made from the creator:
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/d76913a6-3507-4f70-b259-04d32c4b4678)

° There is also a Search view: 
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/2826733e-737c-43c9-b543-874704d8da18)

° When a user is logged, that has a role of PUBLISHER you can see that the Admin button, for adding, deleting and editing users is missing:
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/4cf97bb2-0c9b-4889-8ff1-08c52be4f7db)

° When a user with the role of READER is logged in you can see that also the Add News is missign, meaning he cannot add new article, hense only read them:
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/2ddca11f-1ec3-4390-8746-1dd46861618d)
-> In the details of the News by the reader, the Delete and Edit Article buttons are missing, hense he can only read the article:
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/b6520dbf-9a66-42d6-b921-dbf954e3d7a3)

° The Passwords in the DB of the users are also being encoded using a password encoder:
![image](https://github.com/BroskiVankis/NewsService/assets/78420177/e35476ec-1f24-429b-944e-c0cec94d44f3)


