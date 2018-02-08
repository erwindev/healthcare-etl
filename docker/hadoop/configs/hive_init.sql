create external table open_payment_record(payerAmount string, payerId string, payerName string, providerId string, providerName string)
stored as parquet
location '/user/erwinalberto/openpayments';