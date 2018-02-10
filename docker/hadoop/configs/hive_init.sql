create external table open_payment_record(payerId string, payerName string, payerAmount string, providerId string, providerName string)
stored as parquet
location '/user/erwinalberto/openpayments';