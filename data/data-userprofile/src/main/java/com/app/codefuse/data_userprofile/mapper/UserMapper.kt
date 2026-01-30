package com.app.codefuse.data_userprofile.mapper

import com.app.codefuse.core_domain.models.User
import com.app.codefuse.data_userprofile.model.UserDto

fun UserDto.toDomain(): User {
    return User(
        fullName = "${name.title} ${name.first} ${name.last}",
        imageUrl = picture.large,
        email = email,
        location = "${location.city}, ${location.country}"
    )
}