package com.app.codefuse.data_userprofile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("results") val results: List<UserDto>,
    @SerialName("info") val info: InfoDto
)

@Serializable
data class UserDto(
    @SerialName("gender") val gender: String,
    @SerialName("name") val name: NameDto,
    @SerialName("location") val location: LocationDto,
    @SerialName("email") val email: String,
    @SerialName("login") val login: LoginDto,
    @SerialName("dob") val dob: DateOfBirthDto,
    @SerialName("registered") val registered: RegisteredDto,
    @SerialName("phone") val phone: String,
    @SerialName("cell") val cell: String,
    @SerialName("id") val id: IdDto,
    @SerialName("picture") val picture: PictureDto,
    @SerialName("nat") val nat: String
)

@Serializable
data class NameDto(
    @SerialName("title") val title: String,
    @SerialName("first") val first: String,
    @SerialName("last") val last: String
)

@Serializable
data class LocationDto(
    @SerialName("street") val street: StreetDto,
    @SerialName("city") val city: String,
    @SerialName("state") val state: String,
    @SerialName("country") val country: String,
    @SerialName("postcode") val postcode: String, // String is safer for postcodes
    @SerialName("coordinates") val coordinates: CoordinatesDto,
    @SerialName("timezone") val timezone: TimezoneDto
)

@Serializable
data class StreetDto(
    @SerialName("number") val number: Int,
    @SerialName("name") val name: String
)

@Serializable
data class CoordinatesDto(
    @SerialName("latitude") val latitude: String,
    @SerialName("longitude") val longitude: String
)

@Serializable
data class TimezoneDto(
    @SerialName("offset") val offset: String,
    @SerialName("description") val description: String
)

@Serializable
data class LoginDto(
    @SerialName("uuid") val uuid: String,
    @SerialName("username") val username: String
    // Omitted sensitive fields like password/salt for brevity, add if needed
)

@Serializable
data class DateOfBirthDto(
    @SerialName("date") val date: String,
    @SerialName("age") val age: Int
)

@Serializable
data class RegisteredDto(
    @SerialName("date") val date: String,
    @SerialName("age") val age: Int
)

@Serializable
data class IdDto(
    @SerialName("name") val name: String,
    @SerialName("value") val value: String?
)

@Serializable
data class PictureDto(
    @SerialName("large") val large: String,
    @SerialName("medium") val medium: String,
    @SerialName("thumbnail") val thumbnail: String
)

@Serializable
data class InfoDto(
    @SerialName("seed") val seed: String,
    @SerialName("results") val results: Int,
    @SerialName("page") val page: Int,
    @SerialName("version") val version: String
)