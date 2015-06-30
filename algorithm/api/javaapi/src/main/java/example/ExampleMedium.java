package example;

import org.aksw.mex.MEXSerializer_10;
import org.aksw.mex.MyMEX_10;
import org.aksw.mex.util.Global;
import org.aksw.mex.util.Global.*;

import java.util.Date;

/**
 * Created by esteves on 27.06.15.
 */
public class ExampleMedium {

    public static void main(String[] args) {

        //the MEX wrapper!
        MyMEX_10 mex = new MyMEX_10();

        //basic authoring provenance
        {
            //change later here the sets for adds
            mex.setAuthorName("D Esteves");
            mex.setAuthorEmail("esteves@informatik.uni-leipzig.de");
            mex.setContext(Global.EnumContext.RecomenderSystems);
            mex.setOrganization("Leipzig University");

            mex.setExperimentId("E001");
            mex.setExperimentTitle("my first experiment");
            mex.setExperimentDate(new Date());
            mex.setExperimentDescription("my first MEX experiment");
        }

        String eid = "E001S001";
        //ml-experiment-configuration
        {
            mex.addConfiguration(eid);

            mex.Configuration(eid).setDescription("analyzing 10-fold cross validation for SVM");
            mex.Configuration(eid).Model().setId("svm20150322");
            mex.Configuration(eid).Model().setDescription("model generated by LibSVM");
            mex.Configuration(eid).Model().setDate(new Date());

            mex.Configuration(eid).SamplingMethod().setName(EnumSamplingMethod.CrossValidation);
            mex.Configuration(eid).SamplingMethod().setTrainSize(0.8);
            mex.Configuration(eid).SamplingMethod().setTestSize(0.2);
            mex.Configuration(eid).SamplingMethod().setFolds(10);
            mex.Configuration(eid).SamplingMethod().setSequential(true);

            mex.Configuration(eid).HardwareConfiguration().setOperationalSystem("ubuntu");
            mex.Configuration(eid).HardwareConfiguration().setCPU(EnumProcessor.INTEL_COREI7);
            mex.Configuration(eid).HardwareConfiguration().setMemory(EnumRandomAccessMemory.SIZE_16GB);
            mex.Configuration(eid).HardwareConfiguration().setHD("SSD");
            mex.Configuration(eid).HardwareConfiguration().setCache(EnumCache.CACHE_3MB);

            mex.Configuration(eid).DataSet().setName("bovespa");
            mex.Configuration(eid).DataSet().setDescription("brazilian stock market 2013");
            mex.Configuration(eid).DataSet().setURI("http://www.bmfbovespa.com.br/shared/iframe.aspx?idioma=pt-br&url=http://www.bmfbovespa.com.br/pt-br/cotacoes-historicas/FormSeriesHistoricas.asp");

            mex.Configuration(eid).addFeature("min");
            mex.Configuration(eid).addFeature("max");
            mex.Configuration(eid).addFeature("ope");
            mex.Configuration(eid).addFeature("clo");

        }

        //adding algorithms and parameters
        {
            mex.Configuration(eid).Implementation().set(EnumImplementation.Weka);
            mex.Configuration(eid).Implementation().setRevision("3.6.6");

            mex.Configuration(eid).addAlgorithm(EnumAlgorithm.SupportVectorMachines);
            mex.Configuration(eid).addAlgorithm(EnumAlgorithm.NaiveBayes);

            mex.Configuration(eid).Algorithm(EnumAlgorithm.SupportVectorMachines).addParameter("C", "10^3");
            mex.Configuration(eid).Algorithm(EnumAlgorithm.SupportVectorMachines).addParameter("alpha", "0.2");
        }

        String ex1 = "EX001";
        String ex2 = "EX002";
        //associate your run x each algorithm
        {
            mex.Configuration(eid).addExecutionOverall(ex1, EnumPhase.TRAIN);
            mex.Configuration(eid).ExecutionOverall(ex1).setStartDate(new Date());
            mex.Configuration(eid).ExecutionOverall(ex1).setAlgorithm(mex.Configuration(eid).Algorithm(EnumAlgorithm.SupportVectorMachines));
            mex.Configuration(eid).ExecutionOverall(ex1).setStartsAtPosition("1233");
            mex.Configuration(eid).ExecutionOverall(ex1).setEndsAtPosition("1376");


                //your models call here !
            mex.Configuration(eid).ExecutionOverall(ex1).setEndDate(new Date());

            mex.Configuration(eid).addExecutionOverall(ex2, EnumPhase.TEST);
            mex.Configuration(eid).ExecutionOverall(ex2).setStartDate(new Date());
            mex.Configuration(eid).ExecutionOverall(ex2).setAlgorithm(mex.Configuration(eid).Algorithm(EnumAlgorithm.SupportVectorMachines));
            mex.Configuration(eid).ExecutionOverall(ex2).setStartsAtPosition("1377");
            mex.Configuration(eid).ExecutionOverall(ex2).setEndsAtPosition("1420");

                //your models call here !
            mex.Configuration(eid).ExecutionOverall(ex2).setEndDate(new Date());

        }

        //saving performances for each run
        {
            mex.Configuration(eid).ExecutionOverall(ex1).addPerformance(EnumMeasures.ACCURACY.toString(), .96);
            mex.Configuration(eid).ExecutionOverall(ex1).addPerformance(EnumMeasures.TRUE_POSITIVE_RATE.toString(), .70);
            mex.Configuration(eid).ExecutionOverall(ex2).addPerformance(EnumMeasures.ERROR.toString(), .04);
            mex.Configuration(eid).ExecutionOverall(ex2).addPerformance(EnumMeasures.ACCURACY.toString(), .83);
            mex.Configuration(eid).ExecutionOverall(ex2).addPerformance(EnumMeasures.TRUE_POSITIVE_RATE.toString(), .61);
        }

        //exporting your ML experiment
        MEXSerializer_10.getInstance().parse();
        MEXSerializer_10.getInstance().saveToDisk("teste.ttl","http://mex.aksw.org/examples/001/", mex);

        System.exit(0);

    }

}
