Feature: Verify articles in Mobile Application
 
  @smoketest @bvttests
  Scenario Outline: Verify article displayed properly
    Given I Launch Mobile Application in <platform>
    And I login with credentials <userName> and <password>
    And I click on artcile <articleType>
    Then I should be see the first article details as expected in main screen and in artcle detail page
    Then I should be able to logout
    

 Examples: 
      | platform  | articleType|userName|password|
      | android | LATEST |digitaltest10|Sphdigital1|
      | android | HOME |digitaltest10|Sphdigital1|
      | android | SINGAPORE |digitaltest10|Sphdigital1|
      | android | ASIA |digitaltest10|Sphdigital1|
 