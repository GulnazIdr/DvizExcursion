package org.gulnazidr.stepik.core.network

class CustomServerException(message: String): Exception(message)
class TokenRefreshException(message: String): Exception(message)
class NothingFoundException(message: String): Exception(message)
class RequestTimeOutException(message: String): Exception(message)