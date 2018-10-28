<template>
    <div class="conversation">
        <!--{{ updateConversation }}-->
        <div v-if="response !== null">
            <div v-for="message in this.response.messages">
                <div v-if="message.sender">
                    <article class="message is-dark">
                        <div class="message-body">
                            {{ message.body }}
                        </div>
                    </article>
                </div>
                <div v-else>
                    <article class="message is-primary">
                        <div class="message-body">
                            {{ message.body }}
                        </div>
                    </article>
                </div>
            </div>
            <div class="field">
                <div class="control">
                    <input v-on:keyup.enter="submit" v-model="message" class="input is-medium" type="text"
                           placeholder="Message...">
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "Conversation",
        props: {
            name: String
        },
        data() {
            return {
                response: null,
                errors: null,
                message: null
            }
        },
        watch: {
            name() {
                axios.get("api/conversation/" + this.name + "/")
                    .then(response => {
                        this.response = response.data
                    })
                    .catch(error => {
                        this.errors.push(error)
                    })

            }

        },
        methods: {
            submit() {
                let content = {
                    "body": this.message,
                    "sender": true
                };
                this.message = '';
                axios.put("api/conversation/" + this.name + "/", content)
                    .catch(error => {
                        this.errors = error
                    });
            }
        },
        updated() {
            if (this.name !== null) {
                axios.get("api/conversation/" + this.name + "/")
                    .then(response => {
                        this.response = response.data
                    })
                    .catch(error => {
                        this.errors.push(error)
                    })
            }
        }
    }
</script>

<style scoped>

</style>