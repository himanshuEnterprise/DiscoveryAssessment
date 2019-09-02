Feature: DiscoveryGo

Scenario: Add two videos from home page and validate it from my-videos page

Given https://go.discovery.com/
When Title of Home page is Discovery - Official Site
Then Scroll home page to RECOMMENDED FOR YOU categories
Then Check number of videos more than two
Then Get titles and description for two videos of homepage and add videos to favorites
Then Navigate to My videos page
Then Validate title and description of both videos