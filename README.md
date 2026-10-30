# Drone Fleet Security Monitor

## Overview

The Drone Fleet Security Monitor is a Java-based application that simulates and monitors a fleet of drones in real time. The program generates telemetry data, detects abnormal drone behavior, stores anomaly records, and views fleet activity.

The system includes:

* Real-time drone telemetry simulation
* Automated anomaly detection
* Database storage of detected anomalies
* Graphical user interface
* Drone map visualization
* Console monitoring output
* CSV anomaly export
* Query tools for historical anomaly data

---

# Requirements

Before running the application, make sure the following are installed:

* Java Development Kit (JDK) 21 or newer
* Java IDE (recommended):

  * IntelliJ IDEA
  * Eclipse
  * VS Code with Java extensions
* SQLite JDBC library

The project uses SQLite for storing and retrieving anomaly records. The SQLite JDBC library 
must be included in the project dependencies before running the application.

---

# Running the Application


1. Open the project in your Java IDE.

2. Locate the main application class:

   ```
   DroneMonitorApp.java
   ```

3. Run the `main()` method.

---

# Using the Application

## Dashboard

When the program starts, the dashboard displays:

* Current drone status
* Battery levels
* Altitude
* Velocity
* Drone locations
* Detected anomalies

The dashboard updates automatically as telemetry data changes.

## To view a specific drone:

1. Click on the name/ID of the drone you want to inspect.
2. The selected drone will become the active drone.
3. The dashboard will display the selected drone's current telemetry information.

---

# Query Anomaly Database

The application uses SQLite to permanently store detected anomaly records.

During startup, the program initializes the SQLite database and creates the database if it does not already exist.

To query the database:

1. Run the program until anomalies are generated.
2. Open the query interface using the button "Query Anomaly Database..." under "Files".
3. Search using one of the following:
   - Drone ID
   - Anomaly type
   - Date range
   - All records

The results should display previously detected events stored in the SQLite database based on the selected query.

---

# Exporting Data

The application supports exporting anomaly history into CSV format.

To test exporting:

1. Use the "Export CSV..." under the File tab.
2. Select a destination file.
3. Open the CSV file and verify the stored information.

The exported data includes:

* Drone ID
* Anomaly type
* Severity
* Description
* Timestamp

---

# Settings

## Pause/Resume Telemetry

The pause button allows the user to temporarily stop telemetry updates and anomaly processing.

When paused:

- Drone telemetry simulation stops updating
- The current dashboard state remains visible
- Previously detected information is preserved

Pressing the button again resumes normal monitoring.

## Mute/Unmute Alerts

The mute button controls whether anomaly alert sounds are enabled.

When audio alerts are muted:

- Visual anomaly notifications continue to appear
- The system continues detecting and recording anomalies
- Sound notifications are disabled

Re-enabling audio restores alert sounds for newly detected anomalies.

---

# Authors

Tyler Lorentz

Andrew Narlock

Bryan Nguyen Do

Roberto Manzano

James Fischer

# Version

1.0
## Also included
- `group-assignment-minesweeper/`: TCSS 360 Group Assignment 1 (Minesweeper hint-grid generator, team submission with a JUnit-style test and large generated input/output).
