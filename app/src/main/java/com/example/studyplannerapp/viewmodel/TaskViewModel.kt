package com.example.studyplannerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyplannerapp.data.local.dao.TaskDao
import com.example.studyplannerapp.data.local.entity.Task
import com.example.studyplannerapp.data.remote.AuthRepository
import com.example.studyplannerapp.data.repository.TaskRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskViewModel(
    private val repository: TaskRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    // Dynamic user
    private val currentUserFlow = authRepository.currentUser

    // Tasks flow
    @OptIn(ExperimentalCoroutinesApi::class)
    val allTasks = currentUserFlow.flatMapLatest { user ->
        repository.getUserTasks(user?.uid ?: "")
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        currentUserFlow.value?.let { user ->
            repository.addTask(task, user)
        }
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        currentUserFlow.value?.let { user ->
            repository.updateTask(task, user)
        }
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        currentUserFlow.value?.let { user ->
            repository.deleteTask(task, user)
        }
    }

    fun clearAll() = viewModelScope.launch {
        currentUserFlow.value?.let { user ->
            repository.clearAll(user)
        }
    }

    fun syncFromCloud() = viewModelScope.launch {
        currentUserFlow.value?.let { user ->
            repository.syncFromFirestore(user)
        }
    }
}

