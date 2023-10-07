# TrackTask Android App Documentation

## Overview

TrackTask is an Android application that allows users to manage their to-do tasks efficiently. It offers features to create, edit, delete, and mark tasks as completed. The app is designed with a user-friendly interface and employs modern Android development principles and libraries.

## App Architecture

The TrackTask app follows the Model-View-ViewModel (MVVM) architecture pattern. This separation of concerns ensures a clear and maintainable structure for the app. Below are the key components of the app's architecture:

- **Model**: Represents the data and business logic. In this app, the primary model is the `Task` class, which defines the structure of a task.

- **View**: Represents the user interface components. Activities and XML layout files are part of the view in this app.

- **ViewModel**: Acts as an intermediary between the model and view. It prepares and manages the data for the view. The `TaskViewModel` class handles interactions between the `MainActivity` (view) and the `TaskRepo` (model).

## Threading and Concurrency

The TrackTask app uses Kotlin Coroutines to manage background tasks and asynchronous operations. This ensures that database operations, which can be time-consuming, don't block the main UI thread, providing a smooth user experience.

## Table of Contents

1. **Introduction**
2. **Features**
3. **Getting Started**
   - 3.1. Prerequisites
   - 3.2. Installation
4. **App Structure**
   - 4.1. XML Layouts
   - 4.2. Kotlin Code
   - 4.3. Database
5. **ViewModels and Repository**
6. **Dependencies**
7. **Room Database**
   - 7.1. Task Entity
   - 7.2. Task DAO (Data Access Object)
   - 7.3. TaskDatabase
   - 7.4. TaskRepo (Repository)
7. **Usage**
8. **Conclusion**

## 1. Introduction

The TrackTask app is a straightforward Android application designed to assist users in managing their tasks. It enables users to create, edit, and delete tasks, all while displaying tasks in a list. This documentation provides a detailed understanding of how the app is structured and how it operates.

## 2. Features

The app boasts the following features:

- **Task Management:** Create, edit, and delete tasks.
- **Task List:** Display tasks in a scrollable list.
- **Splash Screen:** An attractive splash screen for a smooth startup experience.

## 3. Getting Started

### 3.1. Prerequisites

Before you begin working with the TrackTask app, ensure you have the following prerequisites:

- **Android Studio:** Install Android Studio on your computer.
- **Basic Knowledge:** A fundamental understanding of Android app development concepts.

### 3.2. Installation

Follow these steps to install and run the TrackTask app:

1. **Clone the Project:** Clone or download the project from the repository.
2. **Open in Android Studio:** Launch Android Studio and open the project directory.
3. **Build and Run:** Build and run the app on an emulator or physical Android device.

## 4. App Structure

### 4.1. XML Layouts

#### `activity_main.xml`

```xml
<!-- Main activity layout with RecyclerView for displaying tasks -->
```

- This XML layout defines the main activity's UI, which includes a `RecyclerView` for displaying the list of tasks and two floating action buttons for adding new tasks and clearing all tasks. The `RecyclerView` is styled with a green background.

#### `task_list.xml`

```xml
<!-- Layout for individual task items in the RecyclerView -->
```

- This layout represents each individual task item in the `RecyclerView`. It includes a `CheckBox` for task completion, an "Edit" button for editing tasks, and a "Delete" button for deleting tasks.

#### `activity_new_task.xml`

```xml
<!-- Layout for creating or editing tasks -->
```

- This layout is used for creating or editing tasks. It includes an `EditText` for entering task descriptions and a "Save" button for saving or updating tasks.

#### `activity_splash_screen.xml`

```xml
<!-- Splash screen layout displayed during app startup -->
```

- This layout defines the splash screen UI, which displays an image while the app loads.

### 4.2. Kotlin Code

#### `MainActivity.kt`

```kotlin
// Main activity for task management
```

- This is the main activity of the app. It sets up the `RecyclerView` to display tasks, handles user interactions, and communicates with the ViewModel to manage tasks. It performs the following functions:
- Initializes and configures the RecyclerView to display tasks.
- Manages user interactions for adding, editing, and clearing tasks.
- Observes changes in the task data using LiveData and updates the UI accordingly.
- Communicates with the ViewModel to perform CRUD operations on tasks.
<h5>Key Functions:</h5>
- <b> onCreate()</b>: Initializes the main activity and sets up UI components.<br>
- <b>deleteListItemClicked()</b>: Deletes a selected task when the delete button is clicked.<br>
- <b>listItemClicked()</b>: Opens the NewTaskActivity for editing a selected task.<br>
- <b>newTaskLauncher</b>: Handles the result of the NewTaskActivity, updating or saving tasks.<br>
- <b> deleteAllTasks()</b>: Deletes all tasks when the clear button is clicked.<br>

#### `TaskAdapter.kt`

- **Description**: The `TaskAdapter` is an adapter for the RecyclerView in the `MainActivity`. It binds the data from the Room database to the individual views in the RecyclerView.

- **Key Code**: The adapter defines view holders for tasks, inflates the `task_list.xml` layout, and handles user interactions for editing and deleting tasks.

- **Functions**:
  - `onCreateViewHolder()`: Creates new `TaskViewHolder` instances when needed.
  - `onBindViewHolder()`: Binds data to the `TaskViewHolder` and handles user interactions.
  - `TaskViewHolder`: Represents the individual views for each task item and sets click listeners.
  - `TaskComparator`: Compares `Task` objects for efficient updates.

#### `NewTaskActivity.kt`

```kotlin
// Activity for creating or editing tasks
```

- This activity is used for creating or editing tasks. It communicates with the ViewModel to save or update tasks.

#### `SplashScreenActivity.kt`

```kotlin
// Splash screen activity
```

- This activity displays a splash screen with a timer, then launches the main activity.

### 4.3. Database

#### `Task.kt`

```kotlin
// Data class representing a task
```

- This data class defines the data structure for tasks. It is used by Room, the local database library, to create and manage the task table.

#### `TaskDao.kt`

```kotlin
// Data Access Object (DAO) for tasks
```

- This interface defines the database access methods for tasks, including inserting, updating, deleting, and querying tasks.

#### `TaskDatabase.kt`

```kotlin
// Room database class
```

- This class represents the Room database. It defines the database version, entities, and provides a reference to the `TaskDao`.

## 5. ViewModels and Repository
The ViewModel uses LiveData to observe changes in task data and communicates with the `TaskRepo`.

- `TaskViewModel.kt`: This ViewModel class handles UI-related tasks, such as observing changes in the task list and providing methods for performing CRUD (Create, Read, Update, Delete) operations on tasks.

- `TaskViewModelFactory.kt`: This class provides instances of the `TaskViewModel`. It is used to pass the repository to the ViewModel.

- `TaskRepo.kt`: The repository class abstracts the data sources and exposes methods for interacting with tasks. It utilizes the `TaskDao` to perform database operations.The `TaskRepo` class is responsible for managing data operations related to tasks. It serves as an abstraction layer between the ViewModel and the Room database.

## 6. Dependencies

The app relies on various Android libraries and dependencies, including AndroidX components, Room, Kotlin, Material Design components, and more.
Here are the dependencies used in the TrackTask Android app project:

```gradle
dependencies {
    // AndroidX Libraries
    implementation "androidx.appcompat:appcompat:1.6.1"
    implementation "androidx.constraintlayout:constraintlayout:2.1.4"
    implementation "androidx.core:core-ktx:1.12.0"
    implementation "com.google.android.material:material:1.9.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.6.2"
    implementation "androidx.recyclerview:recyclerview:1.2.1"

    // Room Persistence Library
    implementation "androidx.room:room-runtime:2.5.2"
    kapt "androidx.room:room-compiler:2.5.2"
    implementation "androidx.room:room-ktx:2.5.2"

    // Testing Dependencies
    testImplementation "junit:junit:4.13.2"
    androidTestImplementation "androidx.test.ext:junit:1.1.5"
    androidTestImplementation "androidx.test.espresso:espresso-core:3.5.1"

    // Core Splash Screen Library
    implementation "androidx.core:core-splashscreen:1.0.1"

    // Kotlin Standard Library
    implementation "org.jetbrains.kotlin:kotlin-stdlib:1.5.21"
}
```

These dependencies cover a wide range of functionalities, including UI components, database management, testing, and Kotlin language support. They help create a modern and efficient Android app.

## 7. Room Database

Room is used in the TrackTask app for managing the local database. Let's explore how Room is implemented.

### 7.1. Task Entity

```kotlin
@Entity(tableName = "Task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Task_id")
    val id: Int = 0,
    @ColumnInfo(name = "Task_desc")
    val task: String
)
```

- The `Task` class is annotated with `@Entity`, indicating that it represents a database entity.
- It has two fields: `id` (the primary key) and `task` (the task description).

### 7.2. Task DAO (Data Access Object)

```kotlin
@Dao
interface TaskDao {
    @Query("SELECT * FROM Task_table")
    fun getAllTask(): Flow<List<Task>>

    @Query("DELETE FROM Task_table")
    suspend fun deleteAllTasks()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}
```

- The `TaskDao` interface defines the methods for interacting with tasks in the database.
- It includes methods for querying all tasks, deleting all tasks, inserting, updating, and deleting individual tasks.

### 7.3. TaskDatabase

```kotlin
@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, TaskDatabase::class.java, "TASK_DATABASE"
                ).allow

MainThreadQueries()
                    .addCallback(TaskDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
```

- `TaskDatabase` is an abstract class annotated with `@Database`, indicating that it is a Room database.
- It defines the database version and provides an abstract method `taskDao()` to access the `TaskDao`.

### 7.4. TaskRepo (Repository)

```kotlin
class TaskRepo(private val taskDao: TaskDao) {
    val allTasks: Flow<List<Task>> = taskDao.getAllTask()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }
}
```

- `TaskRepo` is the repository class responsible for abstracting data source access.
- It exposes the `allTasks` property as a Flow for observing changes in the task list.
- This class provides methods to insert, update, delete individual tasks, and delete all tasks.

## 8. Usage

1. Launch the app on your Android device.
2. The splash screen appears briefly, followed by the main activity.
3. Click the floating action button with a plus icon to add a new task.
4. Enter a task description and click "Save" to create the task.
5. View the list of tasks in the main activity.
6. Click the checkbox to mark a task as done.
7. Click the "Edit" button to edit a task.
8. Click the "Delete" button to delete a task.
9. Click the trash can icon (floating action button) to delete all tasks.

## 9. Conclusion
The TrackTask Android app follows the MVVM architecture and utilizes modern Android development libraries like Room, LiveData, ViewModel, and Kotlin Coroutines. It provides a user-friendly interface for managing tasks efficiently, from creation and editing to deletion and completion. The separation of concerns in the architecture ensures maintainability and scalability for future enhancements.
<br>You have now explored the detailed documentation for the TrackTask Android app, including a comprehensive explanation of how Room is utilized for database operations. This app offers a simple yet practical way to manage tasks on your Android device. Feel free to modify and extend it to suit your specific needs or use it as a learning resource for Android app development. Happy coding!

https://github.com/suraiya-jahan-bhuiyan-sraboni/Track_Task/assets/85396098/a829b261-3f5b-4aa2-a5f1-2c355d85b547

![Screenshot_20230929-170501](https://github.com/suraiya-jahan-bhuiyan-sraboni/Track_Task/assets/85396098/d6227418-c2dc-4bee-b239-a2c88a7aff7a)![Screenshot_20230929-165837](https://github.com/suraiya-jahan-bhuiyan-sraboni/Track_Task/assets/85396098/9bb9b82c-ca89-405c-8b68-3cf0250ac271)![Screenshot_20230929-165847](https://github.com/suraiya-jahan-bhuiyan-sraboni/Track_Task/assets/85396098/27451bed-96ff-435f-9684-c00f1af3267b)![Screenshot_20230929-170333](https://github.com/suraiya-jahan-bhuiyan-sraboni/Track_Task/assets/85396098/234dc059-51ca-44d4-aa62-b80a370c48bd)![Screenshot_20230929-170345](https://github.com/suraiya-jahan-bhuiyan-sraboni/Track_Task/assets/85396098/aab6a196-8e70-4def-8cbd-065edfcafc93)![Screenshot_20230929-170403](https://github.com/suraiya-jahan-bhuiyan-sraboni/Track_Task/assets/85396098/a54adcd7-d5f1-4abd-a067-0c2b16b140f3)![Screenshot_20230929-170420](https://github.com/suraiya-jahan-bhuiyan-sraboni/Track_Task/assets/85396098/e32cef11-69d7-4175-9853-74d23057a6b5)
