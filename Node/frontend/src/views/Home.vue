<template>
    <div id="app" class="container">
        <div v-if="logged">
            <div class="row">
                <div class="col s3">
                    <conversations-list v-on:switch-conversation="name = $event"
                                        v-on:new-conversation="addConversation = $event"
                                        :newConversation=addConversation />
                </div>
                <div v-if="! addConversation" class="col s5">
                    <conversation :name=name />
                </div>
                <div v-else class="col s5">
                    <new-conversation v-on:new-conversation="addConversation = $event"/>
                </div>
            </div>
        </div>
        <div v-else>
            <login v-on:logged="logged = $event"></login>
        </div>
    </div>
</template>
<script>
    import ConversationList from '@/components/ConversationsList'
    import Conversation from '@/components/Conversation'
    import NewConversation from '@/components/NewConversation'
    import Login from '@/components/Login'
    import 'materialize-css'
    import 'materialize-css/dist/css/materialize.min.css'
    import 'material-icons'

    export default {
        name: "app",
        components: {
            'conversations-list': ConversationList,
            'conversation': Conversation,
            'new-conversation': NewConversation,
            'login': Login
        },
        data() {
            return {
                name: null,
                addConversation: false,
                logged: false,
            }
        }
    }
</script>