# 🛡️ War Game Simulation (Java OOP Project)

This is a console-based simulation game in Java, featuring two generals who manage armies of soldiers, conduct training, purchase units, and fight strategic battles. The project showcases strong object-oriented design, polymorphism, design patterns, and file persistence.

---

## 🚀 Features

- 👑 Generals manage armies and gold reserves  
- 🪖 Soldiers have ranks (Private → Corporal → Captain → Major), gain experience, and promote  
- ⚔️ Battles between generals based on total army strength  
- 🔄 Experience system with rank progression and death by zero experience  
- 🏋️ Training (maneuvers) costs gold and grants experience  
- 💸 Purchasing soldiers (standard or elite) with randomized names  
- 📝 Secretary logs every action: promotions, training, losses, victories, loot  
- 💾 Save/load general state with army and resources via serialization  
- ✅ Unit tested with JUnit 5 for combat logic and rank progression  

---

## 🔍 Example Log Output

[2025-06-16T13:30:14.528] [ZAKUP] Jan bought soldier [1] Marta - PRIVATE (exp: 1)
[2025-06-16T13:30:14.544] [DOŚWIADCZENIE] Jan: soldier 1 Marta increased experience from 1 to 2
[2025-06-16T13:30:14.552] [BITWA] Battle between Jan (power 6) and Paweł (power 8)
[2025-06-16T13:30:14.555] [WYNIK] Winner: Paweł
[2025-06-16T13:30:14.571] [ŁUP] Loot: 6 coins

---

## 🧱 OOP Design

- **AbstractSoldier** – shared logic for all soldier types  
- **Soldier / EliteSoldier** – polymorphic behavior and strength calculations  
- **General** – manages army, resources, and interactions  
- **Secretary / Report** – logs tagged event history with timestamps  
- **BattleService & TrainingService** – clean separation of game logic using Service pattern  
- **ArmyPersistence** – state saving/loading from file  

---

## 🧪 Testing

Tested core logic with:
- Promotion after experience threshold
- Soldier elimination upon reaching 0 experience
- Battle outcome and loot exchange
- Training cost and effect

---

## 🛠️ Technologies

- Java 17
- JUnit 5
- Object-Oriented Programming (OOP)
- CLI-based simulation
- Serialization (Java I/O)

---

## 🎓 Educational Context

This project was developed as part of my advanced object-oriented programming coursework. The design emphasizes clean architecture, testability, polymorphism, and real-time game-like state updates through logging and services.

---

## 📁 File Structure

├── models/
│ ├── AbstractSoldier.java
│ ├── Soldier.java / EliteSoldier.java
│ ├── General.java
│ ├── Secretary.java / Report.java
├── services/
│ ├── BattleService.java
│ ├── TrainingService.java
│ ├── ArmyPersistence.java
├── test/
│ ├── GeneralTest.java
│ ├── BattleServiceTest.java
├── main/
│ ├── Main.java

