# wx5911xa Pricer

- This is a Pricer used for calls from the mainframe system.
- It is maintained by the internal team, who makes between 1-3 changes annually (there is always a major change for the the FY)
- The the system is built using Java with Serverless Framework.  
    - To deploy the app:
        - load npm & serverless
        - run:
    ```$ serverless deploy````
    - You need to have AWS CLI configured on your system.
- The API URLs are displayed during the deploy 
- Use the x-api-key header with the appropriate key to authenticate against the service
- API keys for security
- Next steps:
    - Documentation
    - Possible caching?
    