# 👟 ShoeDog - Shoe Inventory Management System

A Java-based console application for managing shoe inventory, including brands, models, sizes, colors, and stock levels.

## 🎯 Project Overview

ShoeDog is a console-based inventory management system for a shoe store, providing functionality for:
- Brand management
- Shoe model tracking
- Size and color management
- Stock level monitoring

## ✨ Features

### 🏢 Brand Management
- Add, update, and remove shoe brands
- Search brands by ID or name
- List all available brands

### 📦 Stock Management
- Track individual shoe inventory
- Update stock levels
- Query stock by model, size, or color

### 📏 Size Management
- Predefined size ranges (220-300)
- Size validation
- Size range queries
- Size availability checking

### 🎨 Color Management
- Add, update, and remove colors
- Search colors by ID or name
- Case-insensitive color name search

## 🛠️ Technical Stack

- Java
- JUnit 5 for testing
- Gradle for build management

## 📁 Project Structure

```
src/
├── main/java/com/
│   ├── model/           # Data models
│   │   ├── Colors.java
│   │   ├── Model.java
│   │   ├── Sizes.java
│   │   └── Stock.java
│   ├── service/         # Business logic
│   │   ├── ColorManager.java
│   │   ├── SizeManager.java
│   │   └── StockManager.java
│   └── Application.java
└── test/java/com/       # Test classes
    └── service/
        ├── ColorManagerTest.java
        ├── SizeManagerTest.java
        └── StockManagerTest.java
```

## 💾 Database Structure

The system uses the following database tables:
- model (id, modelname, brandname, listprice, description)
- color (id, color)
- sizes (id, size)
- stock (shoe_id, model_id, color, size, stock)

## 🚀 Getting Started

### 📋 Prerequisites
- Java JDK 11 or higher
- Gradle

### 🏗️ Building the Project
```bash
./gradlew build
```

### 🏃‍♂️ Running the Application
```bash
./gradlew run
```

## 📝 Usage

The application runs in the console and provides a menu-driven interface for:
1. Managing shoe models and brands
2. Tracking inventory levels
3. Managing available sizes and colors
4. Querying stock information

## 🧪 Testing

Run the test suite:
```bash
./gradlew test
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details. 