
# AmazingShop Android Application

AmazingShop is a scalable Android e-commerce application built with **Kotlin** and **Jetpack Compose**, following the **MVVM (Model-View-ViewModel)** architecture. The app allows users to browse a catalog of products, add items to the wishlist, and manage items in the cart. Application state is saved using **Room Database**, and **Hilt** is used for dependency injection. The app also features smooth **shared transition animations** between screens.

## Features

- **Add to Wishlist**: Users can add products to their wishlist.
- **Add to Cart**: Users can add products to the shopping cart.
- **Data Persistence**: State is persisted using Room Database for consistency across sessions.
- **Smooth Transitions**: Shared transition animations provide a seamless user experience.
- **Modular Architecture**: The app is organized into multiple modules for better scalability and maintainability.

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Hilt
- **Database**: Room Database
- **Animation**: Shared Transition Animations
- **Dependency & Plugin Management**: Convention Plugin

## Project Architecture

The project follows a **modular architecture** that separates concerns and makes it easier to scale and maintain. The core logic is divided into multiple feature and core modules.

### Modules

#### Core Modules:
1. **Common**: 
   - Contains shared classes and utilities (e.g., loading state classes like Success, Failure, Loading) used across the app.
   
2. **Data**: 
   - Handles data fetching and storage logic, including repository implementations.

3. **Database**: 
   - Manages local data persistence using Room.

4. **Network**: 
   - Handles network requests and fetching data from remote sources.

5. **Design System**: 
   - Contains the app's theme, typography, and reusable UI components.

6. **Model**: 
   - Contains domain models representing the entities in the app.

#### Feature Modules:
1. **Catalog**: 
   - Displays a list of products for users to browse.
   - Supports interactions for viewing product details, adding products to cart, and adding products to wishlist.

2. **Wishlist**: 
   - Displays a list of items the user has added to their wishlist.

### Modular Design

Modularization ensures the codebase is scalable and easy to maintain. Each module focuses on a specific feature or aspect of the application.

```
AmazingShop/
│
├── core/
│   ├── common/
│   ├── data/
│   ├── database/
│   ├── network/
│   ├── design-system/
│   ├── model/
│
├── features/
│   ├── catalog/
│   ├── wishlist/
│
└── tests/
    └── repository/   # Unit tests for repository classes
```

## Testing

Unit tests are provided as a sample, particularly focusing on the repository class to ensure that data fetching and storage functions work as expected.

<p float="left">
  <img src="https://github.com/ymatinfard/AmazingShop/blob/develop/screenshots/screenshot_catalog.png" alt="Catalog Screenshot" width="400" height="800" />
  <img src="https://github.com/ymatinfard/AmazingShop/blob/develop/screenshots/screenshot_detail.png" alt="Detail Screenshot" width="400" height="800" /> 
  <img src="https://github.com/ymatinfard/AmazingShop/blob/develop/screenshots/screenshot_wishlist.png" alt="Wishlist Screenshot" width="400" height="800" /> 
</p>
