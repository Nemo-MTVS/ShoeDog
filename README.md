# ShoeDog - Shoe Inventory Management System

A Java-based backend system for managing shoe inventory, including brands, models, sizes, colors, and stock levels.

## Project Overview

ShoeDog is designed to handle the complete inventory management system for a shoe store, providing functionality for:
- Brand management
- Shoe model tracking
- Size and color management
- Stock level monitoring
- Model-Color relationship management

## Features

### Brand Management
- Add, update, and remove shoe brands
- Search brands by ID or name
- List all available brands

### Stock Management
- Track individual shoe inventory
- Update stock levels
- Query stock by model, size, or color
- Monitor stock across different locations

### Size Management
- Predefined size ranges (220-300)
- Size validation
- Size range queries
- Size availability checking

### Color Management
- Add, update, and remove colors
- Search colors by ID or name
- Case-insensitive color name search

### Model-Color Link Management
- Link shoe models with available colors
- Bulk color assignment to models
- Query models by color and vice versa
- Update and remove model-color relationships

## Technical Stack

- Java
- JUnit 5 for testing
- Gradle for build management

## Project Structure

```
src/
├── main/java/com/
│   ├── model/           # Data models
│   │   ├── Brands.java
│   │   ├── Colors.java
│   │   ├── ModelColorLink.java
│   │   ├── ShoeModel.java
│   │   ├── Sizes.java
│   │   └── Stock.java
│   ├── service/         # Business logic
│   │   ├── BrandManager.java
│   │   ├── ColorManager.java
│   │   ├── ModelColorLinkManager.java
│   │   ├── SizeManager.java
│   │   └── StockManager.java
│   └── Application.java
└── test/java/com/       # Test classes
    └── service/
        ├── BrandManagerTest.java
        ├── ColorManagerTest.java
        ├── ModelColorLinkManagerTest.java
        ├── SizeManagerTest.java
        └── StockManagerTest.java
```

## Database Structure

The system is designed with the following database tables:
- Brands
- ShoeModels
- Sizes
- Colors
- ModelColorLink
- Stock

## Getting Started

### Prerequisites
- Java JDK 11 or higher
- Gradle

### Building the Project
```bash
./gradlew build
```

### Running Tests
```bash
./gradlew test
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 