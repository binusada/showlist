
package com.app.codefuse.user_profile.ui.details

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.app.codefuse.core_domain.models.User
import com.app.codefuse.core_domain.repository.UserProfileRepository
import com.app.codefuse.user_profile.navigation.USER_ID_ARG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ProfileDetailViewModelTest {

    private lateinit var viewModel: ProfileDetailViewModel
    private val userRepository: UserProfileRepository = mock()
    private val savedStateHandle: SavedStateHandle = mock()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `when userId is provided, user data is fetched and exposed`() = runTest {
        // Given
        val userId = "123"
        val expectedUser = User(
            id = userId,
            fullName = "John Doe",
            email = "john.doe@example.com",
            imageUrl = "https://example.com/image.png",
            location = "london"
        )
        whenever(savedStateHandle.get<String>(USER_ID_ARG)).thenReturn(userId)
        whenever(userRepository.fetchProfiles(50)).thenReturn(listOf(expectedUser))

        // When
        viewModel = ProfileDetailViewModel(userRepository, savedStateHandle)

        // Then
        viewModel.user.test {
            assertEquals(expectedUser, awaitItem())
        }
    }

    @Test
    fun `when userId is not provided, user data is null`() = runTest {
        // Given
        whenever(savedStateHandle.get<String>(USER_ID_ARG)).thenReturn(null)

        // When
        viewModel = ProfileDetailViewModel(userRepository, savedStateHandle)

        // Then
        viewModel.user.test {
            assertNull(awaitItem())
        }
    }

    @Test
    fun `when repository throws an exception, user data is null`() = runTest {
        // Given
        val userId = "123"
        whenever(savedStateHandle.get<String>(USER_ID_ARG)).thenReturn(userId)
        whenever(userRepository.fetchProfiles(50)).thenThrow(RuntimeException("Network error"))

        // When
        viewModel = ProfileDetailViewModel(userRepository, savedStateHandle)

        // Then
        viewModel.user.test {
            assertNull(awaitItem())
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
