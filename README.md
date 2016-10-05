# FertileLandArea
Requirements:
* Java 8
* Gradle (to compile)

To Run:
* unzip the distributions.zip, navigate to fertile-land-area-0.0.1-SNAPSHOT\bin and run fertile-land-area.bat

Test Reports:
* test-reports.zip
    - Contains and jacoco code-coverage report and unit test reports
* test-results.zip
    - Unit test results

Performance:
    This is a very efficient implementation to find contiguous fertile land area when there are less number barren lands
    However if there are large number of barren land scattered across the fertile land then we'd have to re-think the implementation or tweak the existing logic for performance.
    To enhance the performance, we can divide the agricultural land in smaller chunks and process them simultaneously and then combine them back together and reconcile the contiguous areas.