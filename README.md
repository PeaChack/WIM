# Warframe Inventory Manager
The application provides simple manager for in-game inventory with market assistant, that will help newbie players to find good deals.
The main idea is to store player's items, periodically retrieve item prices from the warframe.market API and find the most profitable deals corresponding to player's inventory.

## Backend
### Technologies
Backend part is designed as a monolithic service using the Spring Framework, PostgreSQL as database, Redis as cache. Configured httpBasic authentication.

#### Main entities
 - User - a user of application
 - Item - an in-game item, having some attributes like type, name, urlName
 - Inventory - players inventory, having list of items and their counts. One-to-One relationship to user
 - InventoryItem - intermediate entity, implements Many-to-Many relationship of Item and Inventory with additional "count" attribute
 - ItemPrice - the price of the item on the unofficial in-game items market (warframe.market)

#### Services 
 - UserService - a service for managing user entities
 - ItemService - a service for managing item entities. It will be authorized for admins only.
 - InventoryService - a service for managing the users inventories, including their InventoryItem lists.
 - DealService - a service for searching profitable deals based on specified username and number of deals.
 - Optimizer - a service for calculating profit metrics of individual items and sets of items.

#### Controllers
 - AuthController - a controller that handles registration requests.
 - DealController - a controller that handles deal requests.
 - InventoryController - controller, that responsible for handling POST and GET inventory requests.
 - ItemController - responsible for handling POST and GET item requests.

## Future plans
 - Rework code of the Optimizer service. Make it more simple and clear.
 - Schedule regular update of market prices to facilitate further analytics based on price history.
 - Develop the front-end component on separate repository using React.js.
 - Implement handling of various exceptions, such as validation, unstable connection to warframe.market and so on.
 - Add internationalization. Alter item table to add different languages columns and add some property files with system messages on different languages.
 - Think about migrating of an items db to MongoDB or to store items in JSON format. The reason is the complicated data structure of items.
