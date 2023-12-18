# CS454_evosuit
This is SBST project for CS454 course.


Replicating and improving the idea from the paper “An Empirical Comparison of EvoSuite and DSpot for Improving Developer-Written Test Suites with Respect to Mutation Score”. In the paper, the developer-written test was seeded into the initial population of the EvoSuite and the genetic operator of the EvoSuite was modified. We intend to adjust the genetic operator used in the paper to produce better results or compare results using other methods (e.g., LLM). Additionally, we might try to apply test implications to test generation tools other than EvoSuite.

You can refer evosuit from here. https://github.com/test-amplification/EvoSuiteAmp-framework

## Test Amplification using LLM
To perform test amplification using LLM, we provide Docker image that include our source code and Defects4J.

1. Pull the image


    docker pull cjs0410/llm_amp:latest



2. Start docker container


    docker run -dt —name llm_amp -v $(pwd)/docker/workspace:/root/workspace cjs0410/llm_amp:latest

3. Execute the bash shell


    docker exec -it llm_amp bash

4. Execute llm_amp.py with test mode or execution mode

₩₩₩
    python llm_amp.py test
₩₩₩

or


    python llm_amp.py exec








