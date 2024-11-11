## Project Overview

In this project, I'm following standard best practices and using **Clean Architecture** adapted for Android with three main layers: **Data**, **Domain**, and **Presentation**. Below is a breakdown of each layer.

### Packages

- Each screen has a dedicated package.
- A `base` package contains utilities and reusable components.

### General Practices

1. **Concurrency**: Coroutines and Flow
2. **Architecture**: Clean Architecture + MVI + SSOT (Single Source of Truth)
3. **Reusability**: Components are designed for future feature delivery.
4. **Dependency Injection**: Koin

### Data Layer

This layer contains repositories that implement the **Single Source of Truth (SSOT)** principle. Room is used as the Database ORM library.

### Domain Layer

The domain layer includes **UseCases** that are independent of the Android platform, making it easier to extend to **Kotlin Multiplatform Mobile (KMM)** in the future. UseCases also enhance reusability across different screens.

### Presentation Layer

The Presentation layer is based on the **MVI** (Model-View-Intent) approach. ViewModels manage screen state, which simplifies handling concurrency and fragment instances.

---

## Areas for Improvement

1. **UI Enhancements**: Improve the UI by adding loading indicators and error messages. Dates should be displayed in a readable format, not as timestamps.
2. **RecyclerViews**: Adapters and ViewHolders should be reusable, and DiffUtil should be applied to optimize screen updates.
3. **Code Improvements**: Refer to project TODOs for further improvements.
4. **Remote Logic**: Pagination is a must. WebSockets are also must.
