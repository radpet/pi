./gradlew fatJar \
&& echo "Starting pi-bench" \
&& java -jar build/libs/pi-bench-all.jar -mode single -precision 10000 -num_reps 3 -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode shared -precision 10000 -num_reps 3 -silent \
&& echo "#######" \
&& echo "Parallel threaded factorial" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 4 -precision 10000 -num_reps 3 -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 6 -precision 10000 -num_reps 3 -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 8 -precision 10000 -num_reps 3 -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 12 -precision 10000 -num_reps 3 -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 16 -precision 10000 -num_reps 3 -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 18 -precision 10000 -num_reps 3 -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 20 -precision 10000 -num_reps 3 -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 32 -precision 10000 -num_reps 3 -silent \
&& echo "#######" \
&& echo "Parallel cached factorial" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 4 -precision 10000 -num_reps 3  -fact_mode cached -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 6 -precision 10000 -num_reps 3  -fact_mode cached -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 8 -precision 10000 -num_reps 3  -fact_mode cached -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 12 -precision 10000 -num_reps 3  -fact_mode cached -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 16 -precision 10000 -num_reps 3  -fact_mode cached -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 18 -precision 10000 -num_reps 3  -fact_mode cached -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 20 -precision 10000 -num_reps 3  -fact_mode cached -silent \
&& echo "#######" \
&& java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 32 -precision 10000 -num_reps 3  -fact_mode cached -silent