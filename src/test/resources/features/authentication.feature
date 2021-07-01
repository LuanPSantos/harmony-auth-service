Feature: Authentication
  I as a system's user
  Want to be authenticate by my credential
  To get access to system

  Scenario Outline: system's user successfully authenticated
    Given a credential composed by "<email>" and "<password>"
    When authentication succeed
    Then should receive the authorization tokens
    Examples:
      | email           | password      |
      |email@email.com  |my-password    |

  Scenario Outline: system's user failed authentication by invalid credentials
    Given a credential composed by "<email>" and "<password>"
    When authentication fail by invalid credential
    Then should receive a error message with text "email or password is invalid"
    Examples:
      | email           | password      |
      |email@email.com  |wrong-password |
      |wrong@email.com  |my-password    |

