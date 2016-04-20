package log4mex;

import org.aksw.mex.log4mex.MEXSerializer;
import org.aksw.mex.log4mex.MyMEXVO;
import org.aksw.mex.log4mex.algo.AlgorithmVO;
import org.aksw.mex.util.MEXEnum.*;

import java.util.Date;

/**
 * Created by esteves on 27.06.15.
 */
public class Example04_medium {

    public static void main(String[] args) {

        System.out.println("starting example 04...");

        //the MEX wrapper!
        MyMEXVO mex = new MyMEXVO();

        try{

        //basic authoring provenance
        {
            //change later here the sets for adds
            mex.setAuthorName("D Esteves");
            mex.setAuthorEmail("esteves@informatik.uni-leipzig.de");
            mex.setContext(EnumContexts.RECOMENDER_SYSTEMS);
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
            mex.Configuration(eid).addModel("svm20150322", "model generated by LibSVM", new Date());

            mex.Configuration(eid).addSamplingMethod(EnumSamplingMethods.CROSS_VALIDATION, 10);
            mex.Configuration(eid).SamplingMethod().setTrainSize(0.8);
            mex.Configuration(eid).SamplingMethod().setTestSize(0.2);
            mex.Configuration(eid).SamplingMethod().setSequential(true);

            mex.Configuration(eid).addHardwareConfiguration("ubuntu", EnumProcessors.INTEL_COREI7, EnumRAM.SIZE_16GB, "SSD", EnumCaches.CACHE_3MB);


            mex.Configuration(eid).DataSet().setName("bovespa");
            mex.Configuration(eid).DataSet().setDescription("brazilian stock market 2013");
            mex.Configuration(eid).DataSet().setURI("http://www.bmfbovespa.com.br/shared/iframe.aspx?idioma=pt-br&url=http://www.bmfbovespa.com.br/pt-br/cotacoes-historicas/FormSeriesHistoricas.asp");

            mex.Configuration(eid).addFeature("min");
            mex.Configuration(eid).addFeature("max");
            mex.Configuration(eid).addFeature("ope");
            mex.Configuration(eid).addFeature("clo");

        }

        //adding algorithms and parameters
        AlgorithmVO alg1;
        {

            mex.Configuration(eid).addImplementation(EnumImplementations.WEKA, "3.6.6");

            alg1 = mex.Configuration(eid).addAlgorithm(EnumAlgorithms.SupportVectorMachines);
            mex.Configuration(eid).addAlgorithm(EnumAlgorithms.NaiveBayes);

            String[] param = {"C", "10^3", "alpha", "0.2"};
            alg1.addParameter(param);
        }

        String ex1 = "EX001";
        String ex2 = "EX002";
        //associate your run x each algorithm
        {
            mex.Configuration(eid).addExecution(EnumExecutionsType.OVERALL, EnumPhases.TRAIN);
            mex.Configuration(eid).setExecutionId(0, ex1);

            mex.Configuration(eid).Execution(ex1).setStartDate(new Date());
            mex.Configuration(eid).Execution(ex1).setAlgorithm(mex.Configuration(eid).Algorithm(EnumAlgorithms.SupportVectorMachines));
            mex.Configuration(eid).Execution(ex1).setStartsAtPosition("1233");
            mex.Configuration(eid).Execution(ex1).setEndsAtPosition("1376");



                //your models call here !
            mex.Configuration(eid).Execution(ex1).setEndDate(new Date());

            mex.Configuration(eid).addExecution(EnumExecutionsType.OVERALL, EnumPhases.TEST);
            mex.Configuration(eid).setExecutionId(1, ex2);

            mex.Configuration(eid).Execution(ex2).setStartDate(new Date());
            mex.Configuration(eid).Execution(ex2).setAlgorithm(mex.Configuration(eid).Algorithm(EnumAlgorithms.SupportVectorMachines));
            mex.Configuration(eid).Execution(ex2).setStartsAtPosition("1377");
            mex.Configuration(eid).Execution(ex2).setEndsAtPosition("1420");

                //your models call here !
            mex.Configuration(eid).Execution(ex2).setEndDate(new Date());

        }

        //saving performances for each run
        {

            mex.Configuration(eid).Execution(ex1).addPerformance(EnumMeasures.ACCURACY, .96);
            mex.Configuration(eid).Execution(ex1).addPerformance(EnumMeasures.TRUEPOSITIVERATE, .70);
            mex.Configuration(eid).Execution(ex2).addPerformance(EnumMeasures.ERROR, .04);
            mex.Configuration(eid).Execution(ex2).addPerformance(EnumMeasures.ACCURACY, .83);
            mex.Configuration(eid).Execution(ex2).addPerformance(EnumMeasures.TRUEPOSITIVERATE, .61);
        }


            try{
                MEXSerializer.getInstance().saveToDisk("/Users/dnes/Github/mexproject/metafiles/log4mex/ex004.ttl", "http://mex.aksw.org/examples/", mex);
            }catch (Exception e){
                System.out.print(e.toString());
            }

            System.out.println("The MEX file [ex004.ttl] has been successfully created: share it ;-)");

        System.exit(0);

        }catch (Exception e){
            System.out.println(e.toString());
        }


    }

}
