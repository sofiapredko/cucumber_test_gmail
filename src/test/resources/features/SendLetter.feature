@LoginProfile
Feature: delete emails and undo it
  Background: User navigates to Gmail Sign In page
    Given I go to URL "https://mail.google.com/"

  Scenario Outline: User log in, check few emails, delete them and undo this action
    Then user fill "<username>" and "<password>"
    Then home page loads, user check few emails and delete them
    Then user click on Undo button and emails are not deleted
    Examples:
      |username  | password |
      |sonyachanter@gmail.com | sonichka13 |



