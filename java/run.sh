./gradlew fatJar \
&& java -jar build/libs/pi-bench-all.jar -mode shared -precision 10000 -num_reps 5 -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 12 -precision 10000 -num_reps 5 -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 16 -precision 10000 -num_reps 5 -silent
