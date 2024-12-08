# Simulation Project

2D-Simulation of the world inhabited by herbivores and predators. The project has command-line interface.

<img width="361" alt="Simulation_screenshot" src="https://github.com/user-attachments/assets/a3d75877-e97a-4ed0-a042-b7b09825f2c6">

---

## Project Description

This project is a simulation of the world consisting of predators, herbivores, and static objects such as rocks, trees, and herbs. Predators move to hunt herbivores, and herbivores look for herbs to eat. The simulation includes a full life cycle, where creatures can die, disappear, or be reborn.

The simulation begins with randomly populating the map with creatures and objects.<br>

There are the following types of entities:

- Predators 🐺. They hunt herbivores 🐰. Each herbivore eaten increases the Predator's health to maximum.
- Herbivores 🐰. They eat herbs 🥕. Each herb eaten increases the Herbivore's health by half of its maximum.
- Herb 🥕. It is a resource that herbivores feed on.
- Static objects (rock 🪨 and tree 🌲) that cannot be interacted with - they just fill space.

---

## Simulation rules

Each turn creatures can move one cell. If creature reaches the target, it eat it. If not, the Creature moves towards the target, its health is reduced by 1 HP (health point). When HP becomes zero, the Creature dies, a skull 💀 appears in its place, and on the next turn it is removed from the World Map.<br> 

During the simulation, a certain number of new Predators, Herbivores and Herbs is generated. This number is calculated as a percentage of the map size, independently of the remaining number of entities on the map.<br>

The simulation may end if there are no herbivores or predators left on the map (typical for small map sizes).

---

## User interaction

When starting the program user can set map size or choose default map.<br>
During the simulation user can pause or quit it, and change the map display settings.<br>

Also, every turn, statistics on eaten resources and dead creatures are displayed on the screen.
