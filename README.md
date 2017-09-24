# projectmanager
Software para planejamento de projetos
Developers:

Always pull before start coding. Just push on the designated branch. Check the issues page.

Using: Python 2.7, Flask, PostgreSQL, Psycopg2.

Get Started:

Git help:

1 - clone the repo

git clone 
2 - switch to your branch

git checkout branchname

3 - do your magic and add then to the project when they're ready

git add .

4 - commit your changes

git commit -m "useful message about your changes"

5 - update your clone and send your changes to the server

git pull git push

Install/Configure/Execute:

Install PostgreSQL:

sudo apt-get install postgresql

Create/Configure Database:

CREATE DATABASE emotional-dev On config.py change the SQLALCHEMY_DATABASE_URI property to match your own postgres credentials.

Create an virtual env:

sudo pip install virtualenv virtualenv venv

Install requirements:

cd emotional/site . venv/bin/activate pip install -r requirements.txt

Execute:

python run.py

Branchs/Documentation:

Check issues page to see what to do or submit new issues. Discuss on the issue page. Use the branch designated there. After you finish, ALWAYS reply to mentioned issue including a detailed explanation of your changes (e.g., Issue 15).

Branch Names

project: closest to master

lab: new ideas

lab-name: new big ideas

yourname: your own branch/personal tests

Others/General:

branch name = AWP0000

A -> action

W -> where/what

P -> priority

0000 -> serial/increment

Rules & Understanding:

Actions:
  C -> create
  M -> modify
  R -> remove
  I -> improve
  F -> fix bug
  
Where/what:
  D -> database
  S -> styles
  C -> code 
  L -> logic
  E -> estruture 
  A -> analisys 

Priorit:
  N -> none/neutral
  S -> somewhat important 
  I -> important
  U -> urgent
Examples

1) UDU0019
    Legend: 19th alteration on the database to include a new table as soon as possible
    On Docs: UDU0019/URGENT - update the database to add a log table. Use branch UDU0019. 
2) ILN0001
    Legend: first logic improvement 
    On Docs: ILN0001 - improve logic on report user method. Class: userController
