package org.example.project.core.network

class CustomServerException(message: String): Exception(message)
class TokenRefreshException(message: String): Exception(message)
class NothingFoundException(message: String): Exception(message)