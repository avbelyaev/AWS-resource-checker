package ru.belyaev.automata.port.adapter

import com.amazonaws.services.ec2.model.{Instance, InstanceState, InstanceStateName, Tag}
import org.scalatest.FunSuite
import ru.belyaev.automata.port.adapter.cloud.aws.AwsInstance

/**
  * @author avbelyaev
  */
class AwsResourceTests extends FunSuite {

  test("should create aws instance from descriptor") {
    // given
    val nameTag = new Tag()
      .withKey("Name")
      .withValue("Bot-1")
    val state = new InstanceState().withName(InstanceStateName.Running)

    // and
    val descriptor = new Instance()
      .withTags(nameTag)
      .withState(state)

    // when
    val awsInstance = new AwsInstance(descriptor)

    // then
    assert("Bot-1".equalsIgnoreCase(awsInstance.name))
    assert(state.getName.equalsIgnoreCase(awsInstance.state))
  }
}
