Feature: Advanced Search
Background:
  Given the user has already closed the newsletter on the home page if present

Scenario: Most expensive mouse
  Given the user is on the home page
  When the user searches for "mouse" and clicks search
    And the user clicks on Sort By Price
    And the user clicks on set descending direction if needed
  Then the results should be displayed
    And the user should see the first item is priced over 100 euro

Scenario: Least expensive mouse
  Given the user is on the home page
  When the user searches for "mouse" and clicks search
    And the user clicks on Sort By Price
    And the user clicks on set ascending direction if needed
  Then the results should be displayed
    And the user should see the first item is priced under 10 euro
 
Scenario: First alphabetical keyboard
  Given the user is on the home page
  When the user searches for "keyboard" and clicks search
    And the user clicks on Sort By Name
    And the user clicks on set ascending direction if needed
  Then the results should be displayed
    And the user should see the first item's title comes before the second item's title alphabetically

Scenario: Last alphabetical keyboard
  Given the user is on the home page
  When the user searches for "keyboard" and clicks search
    And the user clicks on Sort By Name
    And the user clicks on set descending direction if needed
  Then the results should be displayed
    And the user should see the first item's title comes after the second item's title alphabetically

Scenario: Gaming Headphones
  Given the user is on the home page
  When the user searches for "headphones" and clicks search
    And the user clicks on the Gaming Category
  Then the results should be displayed
    And the user should see the first item's title contains the word gaming

Scenario: Wireless Headphones
  Given the user is on the home page
  When the user searches for "headphones" and clicks search
    And the user clicks on the Yes - Bluetooth Wireless Category
  Then the results should be displayed
    And the user should see the first item's title contains the word bluetooth

Scenario: Discounted Webcam
  Given the user is on the home page
  When the user searches for "webcam" and clicks search
    And the user clicks on the OFFERS Category
  Then the results should be displayed
    And the user should see the first item's original price is greater than the discounted price

Scenario: USB Webcam
  Given the user is on the home page
  When the user searches for "webcam" and clicks search
    And the user clicks on the USB Connector Category
    And the user clicks on the first item
    And the user scrolls down to the bottom
  Then the specifications table should be displayed
    And the user should see that the Connector row in the table contains the word usb

Scenario: Motherboard Valid Range
  Given the user is on the home page
  When the user searches for "motherboard" and clicks search
    And the user clicks on the minimum price
    And the user types 200
    And the user clicks on the maximum price
    And the user types 300
    And the user clicks go
  Then the results should be displayed
    And the user should see the first item's price is between 200 and 300

Scenario: Motherboard Invalid Range
  Given the user is on the home page
  When the user searches for "motherboard" and clicks search
    And the user clicks on the minimum price
    And the user types 0
    And the user clicks on the maximum price
    And the user types 1
    And the user clicks go
  Then the results should be displayed
    And the user should see the message Your search returned no results.
