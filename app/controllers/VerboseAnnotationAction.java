package controllers;

import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;


/**
 * Created by yuhuanxi on 16/6/17.
 */
public class VerboseAnnotationAction extends Action<VerboseAnnotation> {
    public CompletionStage<Result> call(Http.Context ctx) {
        if (configuration.value()) {
            Logger.info("Calling action for {}", ctx);
        }
        return delegate.call(ctx);
    }
}
