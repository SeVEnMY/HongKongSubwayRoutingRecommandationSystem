# CS3343

City University of Hong Kong CS3343 Group Project

## Role Assignment

1. Project Manager: JIANG Zhangnan
2. Developer Programmers: HUANG Zixuan, LIANG Qiyuan, ZHANG Jinghuai, GUO Kailun, LI Shiying

## Project Name

**Subway Route Planning System**

## Set Up

1. Make sure you have [JAVA](https://www.java.com/en/) environment deployed.
2. Use [Eclipse](https://www.eclipse.org) IDE with [JUnit](https://junit.org/junit5/) library for test.
3. To install project, run `git clone https://gitlab.com/SeVEnMY/cs3343-route-planning-system.git` in your command line.
4. For Windows user, install [Sourcetree](https://www.sourcetreeapp.com/) to manage git. 


## Team Meetings

Every weekend

## File Structure

```
.
├── LICENSE
├── README.md
└── RoutePlanningSystem
    ├── RoutePlanningSystem.iml
    ├── bin
    │   ├── assets
    │   │   ├── Edge.class
    │   │   ├── Facilities.class
    │   │   ├── Line.class
    │   │   ├── Lines.class
    │   │   ├── Station.class
    │   │   └── Stations.class
    │   ├── controller
    │   │   ├── CmdService.class
    │   │   ├── Main.class
    │   │   └── RPS_System.class
    │   ├── service
    │   │   ├── RecordedService.class
    │   │   ├── SvcGetFacilities.class
    │   │   ├── SvcGetRealTimeTraffic.class
    │   │   ├── SvcLeastTimeRoute.class
    │   │   ├── SvcLeastTransferTime.class
    │   │   └── SvcSearchFacility.class
    │   └── test
    │       ├── EdgeTest.class
    │       ├── FacilitiesTest.class
    │       ├── LeastTimeRouteTest.class
    │       ├── LeastTransferTimeTest.class
    │       ├── LineTest.class
    │       ├── LinesTest.class
    │       ├── MainTest.class
    │       ├── RPS_SystemTest.class
    │       ├── RecordedServiceTest.class
    │       ├── StationTest.class
    │       ├── StationsTest.class
    │       ├── SvcGetFacilitiesTest.class
    │       ├── SvcGetRealTimeTrafficTest.class
    │       └── SvcSearchFacilityTest.class
    ├── data
    │   ├── lines.csv
    │   └── mtr_stations.csv
    └── src
        ├── assets
        │   ├── Edge.java
        │   ├── Facilities.java
        │   ├── Line.java
        │   ├── Lines.java
        │   ├── Station.java
        │   └── Stations.java
        ├── controller
        │   ├── CmdService.java
        │   ├── Main.java
        │   └── RPS_System.java
        ├── service
        │   ├── RecordedService.java
        │   ├── SvcGetFacilities.java
        │   ├── SvcGetRealTimeTraffic.java
        │   ├── SvcLeastTimeRoute.java
        │   ├── SvcLeastTransferTime.java
        │   └── SvcSearchFacility.java
        └── test
            ├── EdgeTest.java
            ├── FacilitiesTest.java
            ├── LeastTimeRouteTest.java
            ├── LeastTransferTimeTest.java
            ├── LineTest.java
            ├── LinesTest.java
            ├── MainTest.java
            ├── RPS_SystemTest.java
            ├── RecordedServiceTest.java
            ├── StationTest.java
            ├── StationsTest.java
            ├── SvcGetFacilitiesTest.java
            ├── SvcGetRealTimeTrafficTest.java
            └── SvcSearchFacilityTest.java
```
