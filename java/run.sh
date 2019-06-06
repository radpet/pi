echo "Starting pi-bench"
java -jar build/libs/pi-bench-all.jar -mode single -precision 10000 -num_reps 1 -silent
java -jar build/libs/pi-bench-all.jar -mode single -precision 10000 -num_reps 1 -fact_mode dc -silent
echo "#######"
echo "Parallel threaded factorial"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 1 -precision 10000 -num_reps 1 -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 4 -precision 10000 -num_reps 1 -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 8 -precision 10000 -num_reps 1 -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 12 -precision 10000 -num_reps 1 -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 16 -precision 10000 -num_reps 1 -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 32 -precision 10000 -num_reps 1 -silent
echo "#######"
echo "Cached factorial"
java -jar build/libs/pi-bench-all.jar -mode single -precision 10000 -num_reps 1 -fact_mode cached -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 1 -precision 10000 -num_reps 1  -fact_mode cached -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 4 -precision 10000 -num_reps 1  -fact_mode cached -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 6 -precision 10000 -num_reps 1  -fact_mode cached -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 12 -precision 10000 -num_reps 1  -fact_mode cached -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 16 -precision 10000 -num_reps 1  -fact_mode cached -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 32 -precision 10000 -num_reps 1  -fact_mode cached -silent
echo "DC factorial"
java -jar build/libs/pi-bench-all.jar -mode single -precision 10000 -num_reps 1 -fact_mode dc -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 1 -precision 10000 -num_reps 1  -fact_mode dc -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 4 -precision 10000 -num_reps 1  -fact_mode dc -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 6 -precision 10000 -num_reps 1  -fact_mode dc -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 12 -precision 10000 -num_reps 1  -fact_mode dc -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 16 -precision 10000 -num_reps 1  -fact_mode dc -silent
echo "#######"
java -jar build/libs/pi-bench-all.jar -mode parallel -num_threads 32 -precision 10000 -num_reps 1  -fact_mode dc -silent