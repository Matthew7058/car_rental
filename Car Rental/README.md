## Scenario

The goal is for a rental agent to be able to generate a statement of the
fees owed to the rental company by a customer. A customer is represented by
a `Customer` class that holds their name and records all of their rentals.
Each rental is represented by a `Rental` class that stores details of the
car that was rented and the time period of the rental.  Cars are represented
by a `Car` class that stores details of the model of the car and its
**price code**.  There is a standard price code and separate price codes
for new models and luxury models.  Different daily rental fees apply
to each price code.

The rental company operates a loyalty scheme in which customers can
accumulate 'frequent renter points'.  They normally get 1 frequent renter
point for each rental, but get an extra bonus point if they are renting
a new model for at least 3 days.