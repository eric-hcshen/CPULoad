package com.tsmc.ta.benchmark;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Benchmark
{
    static final int DEFAULT_RUNS = 1000;
	static final boolean PRINT_DETAILS = false;

    private final String name;
    private final List<TimedTestRunner> runners;
    private final int runs;
    private final StringWriter writer;

    Benchmark(String name, List<TimedTestRunner> runners)
    {
        this(name, runners, DEFAULT_RUNS);
    }

    Benchmark(String name, List<TimedTestRunner> runners, int runs)
    {
        this(name, runners, runs, new StringWriter());
    }

    Benchmark(String name, List<TimedTestRunner> runners, int runs, StringWriter writer)
    {
        this.name = name;
        this.runners = Collections.unmodifiableList(new ArrayList<TimedTestRunner>(runners));
        this.runs = runs;
        this.writer = writer;
    }

    public void run()
    {
        writer.write("Benchmark: ");
        writer.write(name);
        writer.write("\n");

        if (PRINT_DETAILS) {
			// write header
        	writer.write("#");
        	for (TimedTestRunner runner : runners)
        	{
            	writer.write("\t");
            	writer.write(runner.getName());
        	}
        	writer.write("\n");
		}

        for (int i = 0; i < runs; i++)
        {
            if (PRINT_DETAILS) writer.write(i);
            for (TimedTestRunner runner : runners)
            {
                if (PRINT_DETAILS) {
					writer.write("\t");
                	writer.write("" + runner.run() + "\n");
				}
				else runner.run();
            }
            if (PRINT_DETAILS) writer.write("\n");
        }
        
        writer.write("\n");
        writer.write("TOTALS" + "\n");
        writer.write("----\t----\t----\t----\t----\n");
        writer.write("stat\tavg\tmedian\tmin\tmax\n");
        writer.write("----\t----\t----\t----\t----\n");
        for (TimedTestRunner runner : runners)
        {
            writer.write("" + runner + "\n");
        }
        writer.write("----\t----\t----\t----\t----\n");
        writer.write("All times are in nanoseconds.\n");
        
        //writer.flush();
    }
}
