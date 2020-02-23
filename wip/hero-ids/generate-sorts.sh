#!/bin/bash

awk -F, '($1!="" && $1!="HERO"){printf("%.3d %.3d %s\n", $2, $3, $1)}' heros.csv | sort | awk '{printf("%-15s %.3d %.3d\n", $3, $1, $2)}' > by-id.txt
awk -F, '($1!="" && $1!="HERO"){printf("%.3d %.3d %s\n", $3, $2, $1)}' heros.csv | sort | awk '{printf("%-15s %.3d %.3d\n", $3, $2, $1)}' > by-no.txt
