package com.bv.ecommerce.base


/**
 * Common class for API calling
 */

//open class BaseRepository {
//
//    /**
//     * This is the Base suspended method which is used for making the call of an Api and
//     * Manage the Response with response code to display specific response message or code.
//     * @param call ApiInterface method definition to make a call and get response from generic Area.
//     * @param isRetry use when on additional functionality of retry network call with attempt.
//     * isInternetOn Method has been added to check internet before api call
//     */
//    suspend fun <T : Any> makeAPICallWithRetry(
//        call: suspend () -> ResponseData<T>,
//        isRetry: Boolean = false
//    ): ResponseHandler<ResponseData<T>?> {
//        return withContext(Dispatchers.IO) {
//            try {
//                val response = flow { emit(call.invoke()) }
//                    .retryWhen { cause, attempt ->
//                        val shouldRetry = cause is ConnectException || cause is UnknownHostException || cause is SocketTimeoutException
//                        if (shouldRetry && attempt < 10 && isRetry) {
//                            DebugLog.d("Retry Attempt: $attempt")
//                            delay(1000)
//                        } else {
//                            return@retryWhen false
//                        }
//                        shouldRetry
//                    }
//                    .map { Response.success(it) }
//                    .firstOrNull()
//
//                response ?: return@withContext ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, "Null Response", "")
//
//                val statusCode = response.code()
//                val responseBody = response.body()
//                val meta = responseBody?.meta
//
//                return@withContext when {
//                    statusCode in 200..300 -> handleSuccessResponse(meta, responseBody)
//                    statusCode == 401 -> parseUnAuthorizeResponse(response.errorBody())
//                    statusCode == 422 -> parseServerSideErrorResponse(response.errorBody())
//                    statusCode == 500 -> ResponseHandler.OnFailed(
//                        HttpErrorCode.NOT_DEFINED.code, INTERNAL_SERVER_ERROR,
//                        meta?.messageCode.toString()
//                    )
//                    else -> parseUnKnownStatusCodeResponse(response.errorBody())
//                }
//            } catch (e: Exception) {
//                DebugLog.print(e)
//                return@withContext when (e) {
//                    is ConnectionShutdownException -> ResponseHandler.OnFailed(
//                        HttpErrorCode.NO_CONNECTION.code, e.toString(), ""
//                    )
//                    else -> ResponseHandler.OnFailed(
//                        HttpErrorCode.NOT_DEFINED.code, e.toString(), ""
//                    )
//                }
//            }
//        }
//    }
//
//    private fun <T : Any> handleSuccessResponse(
//        meta: Meta?, responseBody: ResponseData<T>?
//    ): ResponseHandler<ResponseData<T>?> {
//        return when (meta?.statusCode) {
//            400, 401 -> ResponseHandler.OnFailed(
//                meta.statusCode, meta.message.toString(), meta.statusCode.toString()
//            )
//            else -> ResponseHandler.OnSuccessResponse(responseBody)
//        }
//    }
//
//    /**
//     * This is the Base suspended method which is used for making the call of an Api and
//     * Manage the Response with response code to display specific response message or code.
//     * @param call ApiInterface method defination to make a call and get response from generic Area.
//     * @param -isRetry use when on additional functionality of retry network call with attempt.
//     */
//    open suspend fun <T : Any> makeAPICall(call: suspend () -> Response<ResponseData<T>>): ResponseHandler<ResponseData<T>?> {
//        return try {
//            val response = call.invoke()
//            val result = when {
//                response.code() in (200..300) -> {
//                    when (response.body()?.meta?.statusCode) {
//                        400 -> ResponseHandler.OnFailed(
//                            response.body()?.meta?.statusCode!!,
//                            response.body()?.meta?.message!!,
//                            "0"
//                        )
//                        401 -> ResponseHandler.OnFailed(
//                            HttpErrorCode.UNAUTHORIZED.code,
//                            response.body()?.meta?.message!!,
//                            response.body()?.meta?.statusCode!!.toString()
//                        )
//                        else -> ResponseHandler.OnSuccessResponse(response.body())
//                    }
//                }
//                response.code() == 401 -> parseUnAuthorizeResponse(response.errorBody())
//                response.code() == 422 -> parseServerSideErrorResponse(response.errorBody())
//                response.code() == 500 -> ResponseHandler.OnFailed(
//                    HttpErrorCode.NOT_DEFINED.code,
//                    INTERNAL_SERVER_ERROR,
//                    response.body()?.meta?.messageCode.toString()
//                )
//                else -> parseUnKnownStatusCodeResponse(response.errorBody())
//            }
//            result
//        } catch (e: Exception) {
//            DebugLog.print(e)
//            when (e) {
//                is ConnectionShutdownException -> ResponseHandler.OnFailed(
//                    HttpErrorCode.NO_CONNECTION.code, e.toString(), ""
//                )
//                else -> ResponseHandler.OnFailed(
//                    HttpErrorCode.NOT_DEFINED.code, e.toString(), ""
//                )
//            }
//        }
//    }
//
//    suspend fun <T : Any> makeAPICallForString(call: suspend () -> Response<T>): ResponseHandler<T?> {
//        try {
//            val response = call.invoke()
//           return when {
//                response.code()==200 -> {
//                    ResponseHandler.OnSuccessResponse(response.body())
//                }
//                response.code() == 401 -> {
//                    return parseUnAuthorizeResponse(response.errorBody())
//                }
//                response.code() == 422 -> {
//                    return parseServerSideErrorResponse(response.errorBody())
//                }
//                response.code() == 500 -> {
//                    return ResponseHandler.OnFailed(
//                        HttpErrorCode.NOT_DEFINED.code,
//                        INTERNAL_SERVER_ERROR,
//                      "500"
//                    )
//                }
//                else -> {
//                    return parseUnKnownStatusCodeResponse(response.errorBody())
//                }
//            }
//        } catch (e: Exception) {
//            DebugLog.print(e)
//            return when (e) {
//                is ConnectionShutdownException -> {
//                    ResponseHandler.OnFailed(HttpErrorCode.NO_CONNECTION.code, e.toString(), "")
//                }
//                is SocketTimeoutException, is IOException, is NetworkErrorException -> {
//                    ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, e.toString(), "")
//                }
//                else -> {
//                    ResponseHandler.OnFailed(HttpErrorCode.NOT_DEFINED.code, e.toString(), "")
//
//                }
//            }
//        }
//    }
//
//
//    /**
//     * Response parsing for 401 status code
//     **/
//    private fun parseUnAuthorizeResponse(response: ResponseBody?): ResponseHandler.OnFailed {
//        val message: String
//        val bodyString = response!!.string()
//        val responseWrapper: ErrorWrapper = JSON.readValue(bodyString)
//        message = if (responseWrapper.meta!!.statusCode == 401) {
//            if (responseWrapper.errors != null) {
//                HttpCommonMethod.getErrorMessage(responseWrapper.errors)
//            } else {
//                responseWrapper.meta.message.toString()
//            }
//        } else {
//            responseWrapper.meta.message.toString()
//        }
//        return ResponseHandler.OnFailed(
//            HttpErrorCode.UNAUTHORIZED.code,
//            message,
//            responseWrapper.meta.messageCode.toString()
//        )
//    }
//
//    /**
//     * Response parsing for 422 status code
//     * */
//    private fun parseServerSideErrorResponse(response: ResponseBody?): ResponseHandler.OnFailed {
//        val message: String
//        val bodyString = response?.string()
//        val responseWrapper: ErrorWrapper = JSON.readValue(bodyString!!)
//
//        message = if (responseWrapper.meta!!.statusCode == 422) {
//            if (responseWrapper.errors != null) {
//                HttpCommonMethod.getErrorMessage(responseWrapper.errors)
//            } else {
//                responseWrapper.meta.message.toString()
//            }
//        } else {
//            responseWrapper.meta.message.toString()
//        }
//        return ResponseHandler.OnFailed(
//            HttpErrorCode.EMPTY_RESPONSE.code,
//            message,
//            responseWrapper.meta.messageCode.toString()
//        )
//    }
//
//    /**
//     * Response parsing for unknown status code
//     * */
//    private fun parseUnKnownStatusCodeResponse(response: ResponseBody?): ResponseHandler.OnFailed {
//        val bodyString = response?.string()
//
//        val result = if (!bodyString.isNullOrEmpty()) {
//            val responseWrapper: ErrorWrapper = JSON.readValue(bodyString)
//            val message = if (responseWrapper.meta?.statusCode == 422) {
//                responseWrapper.errors?.let { HttpCommonMethod.getErrorMessage(it) }
//                    ?: responseWrapper.meta.message.toString()
//            } else {
//                responseWrapper.meta?.message.toString()
//            }
//
//            if (message.isEmpty()) {
//                ResponseHandler.OnFailed(
//                    HttpErrorCode.EMPTY_RESPONSE.code,
//                    message,
//                    responseWrapper.meta?.messageCode.toString()
//                )
//            } else {
//                ResponseHandler.OnFailed(
//                    HttpErrorCode.NOT_DEFINED.code,
//                    message,
//                    responseWrapper.meta?.messageCode.toString()
//                )
//            }
//        } else {
//            ResponseHandler.OnFailed(
//                HttpErrorCode.NOT_DEFINED.code,
//                "Unknown Error",
//                ""
//            )
//        }
//
//        return result
//    }
//}
