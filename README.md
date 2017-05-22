# transformation-company

Solution:


•	Create Robot class to hold the information of the all robots.

•	Create a War interface which can extend the war in different type (e.g. Human, Alien) of wars.

•	Create RobotWar class which implements the War interface to handle the war between robots.

•	Create Result class to carry all the states and messages.

•	In RobotWar class, define BiFunction interface. Interpret all battle rules by this BiFunction interface and add them in 
order to the rule List.

•	In RobotWar class, split the robots in two teams order by overall rating and special robot (e.g. optimum prime)

•	When battle starts, iterate all robots in two teams and go through all battle rules from the rule List. Then return the 
all results of the battle.

•	Analyze all returned results and interpret the states to messages.

