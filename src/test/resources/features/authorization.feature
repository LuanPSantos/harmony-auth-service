Feature: Authorization
  I as an authenticated system's user
  Want to be authorized
  To use the feature's system that are allow to my role

  Scenario Outline: authorized user
    Given an authenticated system's user with email "<email>" and role "<role>"
    And a required role "<role>"
    When the system's user is authorized by his role
    Then should return the authorization data
    Examples:
      | email           | role  |
      |user@email.com   |USER   |
      |admin@email.com  |ADMIN  |

  Scenario Outline: unauthorized user
    Given an authenticated system's user with email "<email>" and role "<role>"
    And a required role "<required-role>"
    When the system's user is unauthorized by his role
    Then should be forbidden to access the resource
    Examples:
      | email           | role  | required-role |
      |user@email.com   |USER   |ADMIN          |
      |admin@email.com  |ADMIN  |USER           |