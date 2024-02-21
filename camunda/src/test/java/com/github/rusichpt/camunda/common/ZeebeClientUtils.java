package com.github.rusichpt.camunda.common;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.DeploymentEvent;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.client.api.response.PublishMessageResponse;
import io.camunda.zeebe.client.api.worker.JobHandler;
import io.camunda.zeebe.client.api.worker.JobWorker;

import java.util.Map;

public class ZeebeClientUtils {

    public static final String HAS_USER = "Activity_19hjktg";

    @SuppressWarnings("UnusedReturnValue")
    public static DeploymentEvent createDeployment(ZeebeClient client, String bpmn) {
        return client.newDeployResourceCommand()
                .addResourceFromClasspath(bpmn)
                .send()
                .join();
    }

    @SuppressWarnings("UnusedReturnValue")
    public static ProcessInstanceEvent createProcess(ZeebeClient client, String processId, String startAtElementId, Map<String, Object> vars) {
        return client.newCreateInstanceCommand()
                .bpmnProcessId(processId)
                .latestVersion()
                .variables(vars)
                .startBeforeElement(startAtElementId)
                .send()
                .join();
    }
    @SuppressWarnings("UnusedReturnValue")
    public static PublishMessageResponse createMessage(ZeebeClient client, String messageName, String correlationKey, Map<String, Object> vars) {
        return client.newPublishMessageCommand()
                .messageName(messageName)
                .correlationKey(correlationKey)
                .variables(vars)
                .send()
                .join();
    }

    public static void cancelProcess(ZeebeClient client, long processInstanceKey) {
        client.newCancelInstanceCommand(processInstanceKey)
                .send()
                .join();
    }

    public static JobWorker openWorker(ZeebeClient client, String jobType, JobHandler handler) {
        return client.newWorker()
                .jobType(jobType)
                .handler(handler)
                .open();
    }
}
