package org.deeplearning4j.examples.feedforward.classification.detectgender.GitHub;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

import java.io.File;

/**
 * "Linear" Data Classification Example
 *
 * Based on the data from Jason Baldridge:
 * https://github.com/jasonbaldridge/try-tf/tree/master/simdata
 *
 * @author Josh Patterson
 * @author Alex Black (added plots)
 * @author Clark Abbott (added explanatory comments and password authentication framework)
 *
 */
public class PasswordAuthentication {


    public static boolean NeuralNetwork() throws Exception {
        //Number to ensure the experiment is repeatable
        int seed = 123;

        //Learning Rate = gradient on the learning curve/loss function. Basically a x^2 function and you start at -1.
        //Goal is to find the goldilocks value on the curve where the slope = 0
        //Loss function is v important
        double learningRate = 0.01;

        //Batch size = number of training examples used in 1 batch.
        int batchSize = 50;

        //One Epoch is when an ENTIRE dataset is passed forward and backward through the neural network only ONCE.
        //Epochs divided into batches because one epoch at once would be too big
        //There are multiple runs of the data through the NN because it helps the NN to learn about the data better
        //Example: 2000 training examples. if batchsize = 500, iterations = 4.
        int nEpochs = 30;


        //Iterations = number of batches needed to complete one epoch

        //Number of data points per input ?
        int numInputs = 11;

        //Number of categories outputted
        int numOutputs = 1;

        //IDK
        int numHiddenNodes = 20;

        //Utilizes .csv files in these specific folders for training data and evaluation data
        final String filenameTrain  = new ClassPathResource("/classification/password_data_train.csv").getFile().getPath();
        final String filenameTest  = new ClassPathResource("/classification/clarkData.csv").getFile().getPath();

        //RecordReader is an object that stores and reads files. We use it in the trainIter constructor.
        //We use CSVRecordReader to read the data in our .csv files

        //Load the training data:
        RecordReader rr = new CSVRecordReader();
        rr.initialize(new FileSplit(new File(filenameTrain)));

        //The iterator that will present the data to the NN when training. Parameters are
        // the csv record reader to get the data, the batch size so it knows how much data to present at once,
        // the place in the csv iterator it can find the label for the training data, and the number of possible labels
        DataSetIterator trainIter = new RecordReaderDataSetIterator(rr,batchSize,0,1);

        //The next RecordReader and DataSetIterator is the one we'll use for the evaluation data.
        // It does all the same things as the other Iterator but it just does it with the test data.

        //Load the test/evaluation data:
        RecordReader rrTest = new CSVRecordReader();
        rrTest.initialize(new FileSplit(new File(filenameTest)));
        DataSetIterator testIter = new RecordReaderDataSetIterator(rrTest,batchSize,0,1);

        //Now that we've prepared the data to be presented to the NN, we have to build the NN framework.
        //This is like creating the settings for our NN to use.
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()

                //Seed is used to introduce "reproducible randomness" into the NN. This randomness is used to initialize
                // the weights before training.
                .seed(seed)

                //This initializes the weights. Xavier = a heuristic that initializes the weights in a better way
                //Xavier = complicated and I think that's all you really need to know rn
                .weightInit(WeightInit.XAVIER)

                //Deals with the loss function seeing how close we are to slope = 0.
                //Nesterov momentum function is good and works well for convex functions like loss
                .updater(new Nesterovs(learningRate, 0.9))

                //Starts making the layers.
                .list()

                //First layer is a DenseLayer. Dense layers are normal layers in a machine learning model. It's called
                // dense because every node in the layer receives input from every layer before it and gives input
                // to the layer after it.
                .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes)
                        // ReLU stands for rectified linear unit, and is a type of activation function.
                        .activation(Activation.RELU)
                        .build())

                //Second layer is the OutputLayer. It takes input from the dense layer before it.

                //LossFunction.XENT is used in binary classification when dealing with cross entropy.
                // Binary classification = it either is this label or its not this label, no percentages involved.
                // Cross entropy = divergence between two probability distributions. In this context, cross entropy is
                // probably determining which label the probability is highest for so the binary classification can
                // return the correct label.
                .layer(1, new OutputLayer.Builder(LossFunction.XENT)
                        //This activation probably uses the Sigmoid function with the loss function \
                        // to give a binary output.
                        .activation(Activation.SIGMOID)

                        .nIn(numHiddenNodes).nOut(numOutputs).build())
                .build();

        //Constructs a multi-layer network using the framework we've created above
        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        //Creates a listener to help us debug the code and see how the network is learning
        model.setListeners(new ScoreIterationListener(10));  //Print score every 10 parameter updates

        //This is all the text on the screen when you run this program. The score shown is how good
        // the model is getting at predicting the labels. Smaller scores = better at predicting.
        //VERY IMPORTANT
        for ( int n = 0; n < nEpochs; n++) {
            model.fit( trainIter );
        }

        System.out.println("Evaluate model....");

        //Class containing methods to evaluate how well the NN did
        Evaluation eval = new Evaluation(numOutputs);

        //testIter was created earlier to store all the testing examples. Now the testing examples are being
        // compared to the predictions the NN made to see how well the NN did.
        while(testIter.hasNext()){
            //Dataset t = an element of evaluation data that is next in the evaluation set
            DataSet t = testIter.next();

            //INDArray = N-dimensional array interface. Each dimension is called an axis.
            //Gets dataset iterator class data
            INDArray features = t.getFeatures();


            //Gets dataset iterator class labels
            INDArray lables = t.getLabels();

            //This is what the NN would predict would happen
            INDArray predicted = model.output(features,false);
            System.out.println("This is what was predicted");
            System.out.println(predicted.toString().charAt(0));
            return predicted.toString().charAt(0)=='1';

//            boolean isClark = predicted.toString().charAt(0)=='1';
            //Compares the actual labels to the predicted ones from the NN and stores them in the eval object
//            eval.eval(lables, predicted);
        }

        //Print the evaluation statistics (everything stored in the eval object)
        //First, ==Evaluation Metrics== ...
        //Second, ==Confusion Matrix== ...
        //Third, **Example finished** ...

        System.out.println("Okay this is my code. Testing getting individual stats from eval. ");
        System.out.println(eval.stats());
        boolean accuracy = eval.stats().charAt(107)=='1';

        //Will print Test is true if the password is judged to be correct
        System.out.println("Test is " +accuracy);
        return accuracy;

        //------------------------------------------------------------------------------------
        //Training is complete. Code that follows is for plotting the data & predictions only


        //I removed the code that came after this that plotted it on a graph.
    }

}
