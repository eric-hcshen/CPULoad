package com.tsmc.ta.benchmark;

import java.io.File;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

public class RandomAccessFileTest implements Callable<Object>
{
    public static void main(String[] args) throws Exception
    {
        int runs;
        try
        {
            runs = (args.length == 0) ? 1000 : Integer.valueOf(args[0]);
        }
        catch (RuntimeException e)
        {
            System.out.println("Usage: java " + RandomAccessFileTest.class + " [noOfTestRuns]");
            throw e;
        }
        //new RandomAccessFileTest(runs).call();
        PrintWriter writer = new PrintWriter(System.out);
        char[] buf = new char[10000];
        //new RandomAccessFileTest(runs).call(1000, new PrintWriter(System.out, true));
        new RandomAccessFileTest(runs).call(writer);
        writer.write(buf);
        System.out.println(buf);
    }

    private final int runs;

    RandomAccessFileTest(int runs)
    {
        this.runs = runs;
    }

    public Object call() throws Exception
    {
        new Benchmark("RandomAccessFile", getTests(), runs).run();

        return null;
    }

    public Object call(PrintWriter writer) throws Exception
    {
        new Benchmark("RandomAccessFile", getTests(), runs, writer).run();

        return null;
    }

    private List<TimedTestRunner> getTests()
    {
        final File tmpDir = new File(System.getProperty("java.io.tmpdir"));
        final File file = new File(tmpDir, "test.txt");

        final AtomicReference<RandomAccessFile> fileRef = new AtomicReference<RandomAccessFile>();

        final TimedTestRunner openFile = new TimedTestRunner("open", new Callable<Object>()
        {
            public Object call() throws Exception
            {
                fileRef.set(new RandomAccessFile(file, "rw"));
                return null;
            }
        });

        final TimedTestRunner readWrite = new TimedTestRunner("r/w", new Callable<Object>()
        {
            public Object call() throws Exception
            {
                fileRef.get().writeChars("This is a stress test written String\n");
                fileRef.get().readLine();
                return null;
            }
        });

        final TimedTestRunner close = new TimedTestRunner("close", new Callable<Object>()
        {
            public Object call() throws Exception
            {
                fileRef.get().close();
                return null;
            }
        });
        final TimedTestRunner delete = new TimedTestRunner("delete", new Callable<Object>()
        {
            public Object call() throws Exception
            {
                file.delete();
                return null;
            }
        });

        return Arrays.asList(new TimedTestRunner[]
        { openFile, readWrite, close, delete });
    }
}